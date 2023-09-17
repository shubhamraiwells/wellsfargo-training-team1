package com.banking.teamone.repository;

import com.banking.teamone.dto.TransactionDto;
import com.banking.teamone.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {
      Transaction findByFromAccountNo(String accountNo);
}
