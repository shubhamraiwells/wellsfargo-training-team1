package com.banking.teamone.service;

import com.banking.teamone.converter.TransactionConverter;
import com.banking.teamone.dto.TransactionDto;
import com.banking.teamone.dto.TransactionRequestDto;
import com.banking.teamone.model.Account;
import com.banking.teamone.model.Transaction;
import com.banking.teamone.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    TransactionConverter converter;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    /**
     * Get a transaction by its unique identifier.
     *
     * @param Id The unique identifier of the transaction.
     * @return A TransactionDto object representing the transaction if found, or null if not found.
     */

    public TransactionDto getTransactionById(Integer Id){
        Transaction transaction=transactionRepository.findById(Id).isPresent()?transactionRepository.findById(Id).get():null;
        assert transaction != null;
        return new TransactionDto(transaction.getId(),transaction.getFromAccountNo(),transaction.getToAccountNo(),transaction.getTransactionAmount(),transaction.getTransactionDate());

    }

    /**
     * Get a transaction by its associated account number.
     *
     * @param accountNo The account number associated with the transaction.
     * @return A TransactionDto object representing the transaction if found, or null if not found.
     */

    public TransactionDto getTransactionByAccountNo(String accountNo){
        Transaction transaction=transactionRepository.findByFromAccountNo(accountNo)!=null?transactionRepository.findByFromAccountNo(accountNo):null;
        assert transaction != null;
        return new TransactionDto(transaction.getId(),transaction.getFromAccountNo(),transaction.getToAccountNo(),transaction.getTransactionAmount(),transaction.getTransactionDate());

    }


    /**
     * Get all transactions associated with a specific account number.
     *
     * @param accountNo The account number to retrieve transactions for.
     * @return A list of TransactionDto objects representing the transactions.
     */

   public List<TransactionDto> getAllTransactionByAccountNo(String accountNo){
       HashMap<Integer,TransactionDto>storage=new HashMap<>();
        List<TransactionDto>transactionFrom= transactionRepository.findAllByFromAccountNo(accountNo).stream().map(el->new TransactionDto(el.getId(),el.getFromAccountNo(),el.getToAccountNo(),el.getTransactionAmount(),el.getTransactionDate())).collect(Collectors.toList());
        List<TransactionDto>transactionTo= transactionRepository.findAllByToAccountNo(accountNo).stream().map(el->new TransactionDto(el.getId(),el.getFromAccountNo(),el.getToAccountNo(),el.getTransactionAmount(),el.getTransactionDate())).collect(Collectors.toList());
//      transactionFrom.addAll(transactionTo);
       List<TransactionDto>finalres=new ArrayList<>();
       for(TransactionDto tdto:transactionFrom){
           storage.putIfAbsent(tdto.getId(), tdto);
       }
       for(TransactionDto tdto:transactionTo){
           storage.putIfAbsent(tdto.getId(), tdto);
       }
       for(Map.Entry<Integer,TransactionDto>el:storage.entrySet()){
//           System.out.println(el.getKey().getFromAccountNo());
           finalres.add(el.getValue());
       }
      return finalres;
   }




    /**
     * Create a new transaction between two accounts.
     *
     * @param transactionRequest The TransactionRequestDto containing the transaction details.
     * @return A message indicating the result of the transaction creation.
     */

  public String createTransaction(TransactionRequestDto transactionRequest){
      Account accFrom= accountService.getAccountById(transactionRequest.getFromAccountNo());
   Account toAccount=    accountService.getAccountById(transactionRequest.getToAccountNo());


       if(accFrom!=null && toAccount !=null){
           if(!accFrom.getIsActive() || !toAccount.getIsActive()){
               return "Some of the accounts are not active";
           }
//           if(accFrom.getTotalBalance().compareTo(transactionRequest.getTransactionAmount())>0){
               accFrom.setTotalBalance(accFrom.getTotalBalance().subtract(transactionRequest.getTransactionAmount()));
               toAccount.setTotalBalance(toAccount.getTotalBalance().add(transactionRequest.getTransactionAmount()));
               accountService.createAccount(accFrom);
               accountService.createAccount(toAccount);
               transactionRepository.save(converter.transactionRequestToTransaction(transactionRequest));
           return "Transactions performed successfully";
//     }

       }
      return "Transaction failed unsufficient balance";



  }

    /**
     * Get a list of all transactions.
     *
     * @return A list of Transaction objects representing all transactions.
     */

    public List<Transaction>getAllTransactions(){
       return transactionRepository.findAll();
  }
}
