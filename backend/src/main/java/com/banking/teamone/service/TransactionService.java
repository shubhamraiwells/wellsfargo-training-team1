package com.banking.teamone.service;

import com.banking.teamone.dto.TransactionDto;
import com.banking.teamone.model.Transaction;
import com.banking.teamone.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    TransactionDto getTransactionById(String Id){
        Transaction transaction=transactionRepository.findById(Id).isPresent()?transactionRepository.findById(Id).get():null;
        assert transaction != null;
        return new TransactionDto(transaction.getId(),transaction.getFromAccountNo(),transaction.getToAccountNo(),transaction.getTransactionAmount(),transaction.getTransactionDate());

    }

    TransactionDto getTransactionByAccountNo(String accountNo){
        Transaction transaction=transactionRepository.findByFromAccountNo(accountNo)!=null?transactionRepository.findByFromAccountNo(accountNo):null;
        assert transaction != null;
        return new TransactionDto(transaction.getId(),transaction.getFromAccountNo(),transaction.getToAccountNo(),transaction.getTransactionAmount(),transaction.getTransactionDate());

    }

}
