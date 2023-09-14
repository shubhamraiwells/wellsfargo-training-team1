package com.banking.teamone.service;

import com.banking.teamone.converter.TransactionConverter;
import com.banking.teamone.dto.TransactionDto;
import com.banking.teamone.dto.TransactionRequestDto;
import com.banking.teamone.model.Transaction;
import com.banking.teamone.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    TransactionConverter converter;
    @Autowired
    private TransactionRepository transactionRepository;

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
  public void createTransaction(TransactionRequestDto transactionRequest){

       transactionRepository.save(converter.transactionRequestToTransaction(transactionRequest));
  }
}
