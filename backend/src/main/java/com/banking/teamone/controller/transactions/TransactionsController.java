package com.banking.teamone.controller.transactions;


import com.banking.teamone.dto.TransactionDto;
import com.banking.teamone.dto.TransactionRequestDto;
import com.banking.teamone.exception.BankAccountExceptions;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.model.TransType;
import com.banking.teamone.security.AuthTokenFilter;
import com.banking.teamone.service.CustomerIbService;
import com.banking.teamone.model.Account;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.service.AccountService;
import com.banking.teamone.service.CustomerIbService;
import com.banking.teamone.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger= LoggerFactory.getLogger(TransactionsController.class);


    /**
     * Endpoint for getting all transactions by account number.
     *
     * @param accountNo The account number to get transactions for.
     * @return A ResponseEntity with a list of TransactionDto objects.
     */
    @GetMapping("/getTransactions")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<List<TransactionDto>>getAllTransactionsByAccount(@RequestParam String accountNo){
        try {
            List<TransactionDto> res = transactionService.getAllTransactionByAccountNo(accountNo);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            logger.info("Exception occured in getting all transactions by account "+e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
    }
    /**
     * Endpoint for getting all transactions by username.
     *
     * @param username The username to get transactions for.
     * @return A ResponseEntity with a list of TransactionDto objects.
     */
    @GetMapping("/getTransactionsByUsername")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<List<TransactionDto>>getAllTransactionsByUsername(@RequestParam String username){
        try {
            CustomerIb customer = customerIbService.getCustomerByUsername(username);
            if (customer == null) {
                throw new BankAccountExceptions.BankAccountNotFoundException();
            }
            List<TransactionDto> res = null;
            if (customer != null) {
                res = transactionService.getAllTransactionByAccountNo(customer.getAccountNo());
            }
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            logger.info("Exception occured in get all transactions by username: "+e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
    }





    /**
     * Endpoint for creating a new transaction.
     *
     * @param transferBody The request body containing transaction details.
     * @return A ResponseEntity with a message indicating the result of the transaction.
     */
    @PostMapping("/createTransaction")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<String>createTransaction(@RequestBody Map<String, String> transferBody) {
        try {
            // Extract transaction amount and username from the request body

            BigDecimal transactionAmount = new BigDecimal(transferBody.get("amount"));
            String username = transferBody.get("username");
            // Check if the transaction amount is valid (greater than zero)

            if (transactionAmount.compareTo(new BigDecimal(0)) <= 0) {
                throw new BankAccountExceptions.InvalidAmountException();
            }

            // Retrieve the customer based on the provided username

            CustomerIb customer = customerIbService.getCustomerByUsername(username);
            String fromAccountNo = customer.getAccountNo();
            String toAccountNumber = transferBody.get("toAccountNo");
            if(fromAccountNo.compareTo(toAccountNumber)==0){
                return new ResponseEntity<>("you can't transfer to your current account choose deposit",HttpStatus.OK);
            }

            // Retrieve the accounts involved in the transaction

            Account fromAccount = accountService.getAccountById(fromAccountNo);
            Account toAccount = accountService.getAccountById(toAccountNumber);
            if (fromAccount == null || toAccount == null) {
                throw new BankAccountExceptions.BankAccountNotFoundException();
            }

            // Check if both accounts are active

            if (toAccount.getIsActive() && fromAccount.getIsActive()) {
                // Check if the source account has sufficient balance

                if (fromAccount.getTotalBalance().compareTo(transactionAmount) > 0) {
                    try {
                        // Create a transaction request

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
        }catch (Exception e){
            logger.info("creating transactions: "+e.getMessage());
            return new ResponseEntity<>("Exception occured in creating transaction",HttpStatus.OK);
        }
        }


    @PostMapping("/withdrawl")
    @Secured("ROLE_USER")
    public ResponseEntity<String>withdrawamount(@RequestBody Map<String,String> withDrawBody){
        try {
            // Extract username and withdrawal amount from the request body

            String username = withDrawBody.get("username");
            BigDecimal amount = new BigDecimal(withDrawBody.get("amount"));
            // Check if the withdrawal amount is valid (greater than zero)

            if (amount.compareTo(new BigDecimal(0)) <= 0) {
                throw new BankAccountExceptions.InvalidAmountException();
            }

            // Retrieve the customer based on the provided username

            CustomerIb customer = customerIbService.getCustomerByUsername(username);
            String accountNo = customer.getAccountNo();
            // Retrieve the user's account

            Account account = accountService.getAccountById(accountNo);

            // Check if the account is active

            if (account.getIsActive()) {

                // Check if the account has sufficient balance for withdrawal

                if (account.getTotalBalance().compareTo(amount) > 0) {
                    try {
                        // Create a transaction for self-withdrawal with a negative amount

                        transactionService.createTransaction(new TransactionRequestDto(accountNo, accountNo, amount.negate(), TransType.SELF_WITHDRAW));
                        // Update the account balance by subtracting the withdrawal amount

                        accountService.createAccount(new Account(accountNo, account.getAccountType(), account.getOwnerId(), account.getIsActive(), account.getAccountActivationDate(), account.getTotalBalance().subtract(amount)));
                    } catch (Exception e) {
                        return new ResponseEntity<>("Error performing withdrawl", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    throw new BankAccountExceptions.InsufficientFundsException();
                }
            } else {
                throw new BankAccountExceptions.AccountBlockedOrInactiveException();
            }
            // Withdrawal successful

            return new ResponseEntity<>("Withdrawal successful", HttpStatus.OK);
        }catch (Exception e){
            logger.info("Exception occured while withdrawing amount: "+e.getMessage());
            return new ResponseEntity<>("Exception occured in withdrawing amount",HttpStatus.OK);
        }
    }


    @PostMapping("/deposit")
    @Secured("ROLE_USER")
    public ResponseEntity<String>depositAmount(@RequestBody Map<String,String> withDrawBody){
        try {
            String username = withDrawBody.get("username");
            BigDecimal amount = new BigDecimal(withDrawBody.get("amount"));
            CustomerIb customer = customerIbService.getCustomerByUsername(username);
            if (amount.compareTo(new BigDecimal(0)) <= 0) {
                throw new BankAccountExceptions.InvalidAmountException();
            }
            String accountNo = customer.getAccountNo();
            Account account = accountService.getAccountById(accountNo);
            if (account.getIsActive()) {
                try {
                    transactionService.createTransaction(new TransactionRequestDto(accountNo, accountNo, amount, TransType.SELF_DEPOSIT));
                    accountService.createAccount(new Account(accountNo, account.getAccountType(), account.getOwnerId(), account.getIsActive(), account.getAccountActivationDate(), account.getTotalBalance().add(amount)));

                } catch (Exception e) {
                    return new ResponseEntity<>("Error performing deposit", HttpStatus.BAD_REQUEST);
                }
            } else {
                throw new BankAccountExceptions.AccountBlockedOrInactiveException();
            }
            return new ResponseEntity<>("Deposit Successful", HttpStatus.OK);
        }catch (Exception e){
            logger.info("Exception occured in depositing amount "+e.getMessage());
            return new ResponseEntity<>("Exception occured in depositing amount",HttpStatus.OK);
        }
    }

}
