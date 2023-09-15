package com.banking.teamone.service;

import com.banking.teamone.converter.TransactionConverter;
import com.banking.teamone.dto.TransactionDto;
import com.banking.teamone.dto.TransactionRequestDto;
import com.banking.teamone.model.Account;
import com.banking.teamone.model.Transaction;
import com.banking.teamone.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    TransactionConverter converter;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

   public TransactionDto getTransactionById(Integer Id){
        Transaction transaction=transactionRepository.findById(Id).isPresent()?transactionRepository.findById(Id).get():null;
        assert transaction != null;
        return new TransactionDto(transaction.getId(),transaction.getFromAccountNo(),transaction.getToAccountNo(),transaction.getTransactionAmount(),transaction.getTransactionDate());

    }

   public TransactionDto getTransactionByAccountNo(String accountNo){
        Transaction transaction=transactionRepository.findByFromAccountNo(accountNo)!=null?transactionRepository.findByFromAccountNo(accountNo):null;
        assert transaction != null;
        return new TransactionDto(transaction.getId(),transaction.getFromAccountNo(),transaction.getToAccountNo(),transaction.getTransactionAmount(),transaction.getTransactionDate());

    }



   public List<TransactionDto> getAllTransactionByAccountNo(String accountNo){
        return transactionRepository.findAllByFromAccountNo(accountNo).stream().map(el->new TransactionDto(el.getId(),el.getFromAccountNo(),el.getToAccountNo(),el.getTransactionAmount(),el.getTransactionDate())).collect(Collectors.toList());
    }


    public List<TransactionDto> getAllTransactionByAccountNoAndDate(String accountNo, Date dateStart, Date dateEnd){
        return transactionRepository.findAllByFromAccountNo(accountNo).stream().filter(el->(el.getTransactionDate().after(dateStart) && el.getTransactionDate().before(dateEnd)) ).map(el->new TransactionDto(el.getId(),el.getFromAccountNo(),el.getToAccountNo(),el.getTransactionAmount(),el.getTransactionDate())).collect(Collectors.toList());
    }




  public String createTransaction(TransactionRequestDto transactionRequest){
      Account accFrom= accountService.getAccountById(transactionRequest.getFromAccountNo()).isPresent()?accountService.getAccountById(transactionRequest.getFromAccountNo()).get():null;
   Account toAccount=    accountService.getAccountById(transactionRequest.getToAccountNo()).isPresent()?accountService.getAccountById(transactionRequest.getToAccountNo()).get():null;

       if(accFrom!=null && toAccount !=null){
           if(!accFrom.getIsActive() || !toAccount.getIsActive()){
               return "Some of the accounts are not active";
           }
           if(accFrom.getTotalBalance().compareTo(transactionRequest.getTransactionAmount())>0){
               accFrom.setTotalBalance(accFrom.getTotalBalance().subtract(transactionRequest.getTransactionAmount()));
               toAccount.setTotalBalance(toAccount.getTotalBalance().add(transactionRequest.getTransactionAmount()));
               accountService.createAccount(accFrom);
               accountService.createAccount(toAccount);
               transactionRepository.save(converter.transactionRequestToTransaction(transactionRequest));
           return "Transactions performed successfully";
           }

       }
      return "Transaction failed unsufficient balance";



  }
}
