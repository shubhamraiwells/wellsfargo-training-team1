package com.banking.teamone.repository;

import com.banking.teamone.dto.TransactionDto;
import com.banking.teamone.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
      Transaction findByFromAccountNo(String accountNo);
      List<Transaction> findAllByFromAccountNo(String accountNo);
      List<Transaction> findAllByToAccountNo(String accountNo);
}
