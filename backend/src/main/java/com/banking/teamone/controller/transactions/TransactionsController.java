package com.banking.teamone.controller.transactions;


import com.banking.teamone.dto.TransactionDto;
import com.banking.teamone.dto.TransactionRequestDto;
import com.banking.teamone.exception.BankAccountExceptions;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.model.TransType;
import com.banking.teamone.service.CustomerIbService;
import com.banking.teamone.model.Account;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.service.AccountService;
import com.banking.teamone.service.CustomerIbService;
import com.banking.teamone.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    @Autowired
    private  TransactionService transactionService;

    @Autowired
    private CustomerIbService customerIbService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/getTransactions")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<List<TransactionDto>>getAllTransactionsByAccount(@RequestParam String accountNo){
        List<TransactionDto>res=transactionService.getAllTransactionByAccountNo(accountNo);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/getTransactionsByUsername")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<List<TransactionDto>>getAllTransactionsByUsername(@RequestParam String username){
        CustomerIb customer=customerIbService.getCustomerByUsername(username);
        if(customer == null) {
            throw new BankAccountExceptions.BankAccountNotFoundException();
        }
        List<TransactionDto> res=null;
        if(customer!=null) {
            res = transactionService.getAllTransactionByAccountNo(customer.getAccountNo());
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }



//    @GetMapping("/getTransactionsByDate")
//    @Secured({"ROLE_USER","ROLE_ADMIN"})
//    public ResponseEntity<List<TransactionDto>>getAllTransactionsByAccount(@RequestParam("accountNo") String accountNo, @RequestParam(value="fromDate") @DateTimeFormat(pattern="dd-MM-yyyy") Date fromDate,@RequestParam(value="fromDate")  @DateTimeFormat(pattern="dd-MM-yyyy") Date toDate){
//        List<TransactionDto>res=transactionService.getAllTransactionByAccountNoAndDate(accountNo,fromDate,toDate);
//        return new ResponseEntity<>(res, HttpStatus.OK);
//    }

    @PostMapping("/createTransaction")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<String>createTransaction(@RequestBody Map<String, String> transferBody) {
        BigDecimal transactionAmount = new BigDecimal(transferBody.get("amount"));
        String username = transferBody.get("username");
        if(transactionAmount.compareTo(new BigDecimal(0))<=0){
            throw new BankAccountExceptions.InvalidAmountException();
        }
        CustomerIb customer = customerIbService.getCustomerByUsername(username);
        String fromAccountNo = customer.getAccountNo();
        String toAccountNumber = transferBody.get("toAccountNo");
        Account fromAccount = accountService.getAccountById(fromAccountNo);
        Account toAccount = accountService.getAccountById(toAccountNumber);
        if(fromAccount == null || toAccount == null) {
            throw new BankAccountExceptions.BankAccountNotFoundException();
        }
        if (toAccount.getIsActive() && fromAccount.getIsActive()) {
            if (fromAccount.getTotalBalance().compareTo(transactionAmount) > 0) {
                try {
                    TransactionRequestDto transaction = TransactionRequestDto.builder()
                            .fromAccountNo(fromAccountNo)
                            .toAccountNo(toAccountNumber)
                            .transactionAmount(transactionAmount).transType(TransType.TRANSFER)
                            .build();
                    transactionService.createTransaction(transaction);
                } catch (Exception e) {
                    return new ResponseEntity<>("Error performing transaction", HttpStatus.BAD_REQUEST);
                }
            } else {
                throw new BankAccountExceptions.InsufficientFundsException();
            }
        } else {
            throw new BankAccountExceptions.AccountBlockedOrInactiveException();
        }
        return new ResponseEntity<>("Transaction Registered", HttpStatus.OK);
    }

    @PostMapping("/withdrawl")
    @Secured("ROLE_USER")
    public ResponseEntity<String>withdrawamount(@RequestBody Map<String,String> withDrawBody){
        String username=withDrawBody.get("username");
        BigDecimal amount=new BigDecimal(withDrawBody.get("amount"));
        if(amount.compareTo(new BigDecimal(0))<=0){
            throw new BankAccountExceptions.InvalidAmountException();
        }
        CustomerIb customer=customerIbService.getCustomerByUsername(username);
        String accountNo=customer.getAccountNo();
        Account account=accountService.getAccountById(accountNo);
        if(account.getIsActive()) {
            if (account.getTotalBalance().compareTo(amount) > 0  ) {
                try {
                    transactionService.createTransaction(new TransactionRequestDto(accountNo,accountNo,amount.negate(),TransType.SELF_WITHDRAW));
                    accountService.createAccount(new Account(accountNo, account.getAccountType(), account.getOwnerId(), account.getIsActive(), account.getAccountActivationDate(), account.getTotalBalance().subtract(amount)));
                }catch(Exception e){
                    return new ResponseEntity<>("Error performing withdrawl",HttpStatus.BAD_REQUEST);
                }
            } else {
                throw new BankAccountExceptions.InsufficientFundsException();
            }
        } else {
            throw new BankAccountExceptions.AccountBlockedOrInactiveException();
        }
        return new ResponseEntity<>("Withdrawal successful", HttpStatus.OK);
    }


    @PostMapping("/deposit")
    @Secured("ROLE_USER")
    public ResponseEntity<String>depositAmount(@RequestBody Map<String,String> withDrawBody){
        String username=withDrawBody.get("username");
        BigDecimal amount=new BigDecimal(withDrawBody.get("amount"));
        CustomerIb customer=customerIbService.getCustomerByUsername(username);
        if(amount.compareTo(new BigDecimal(0))<=0){
            throw new BankAccountExceptions.InvalidAmountException();
        }
        String accountNo=customer.getAccountNo();
        Account account=accountService.getAccountById(accountNo);
        if(account.getIsActive()) {
            try {
                transactionService.createTransaction(new TransactionRequestDto(accountNo, accountNo, amount, TransType.SELF_DEPOSIT));
                accountService.createAccount(new Account(accountNo, account.getAccountType(), account.getOwnerId(), account.getIsActive(), account.getAccountActivationDate(), account.getTotalBalance().add(amount)));

            } catch (Exception e) {
                return new ResponseEntity<>("Error performing deposit",HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new BankAccountExceptions.AccountBlockedOrInactiveException();
        }
        return new ResponseEntity<>("Deposit Successful", HttpStatus.OK);
    }

}
