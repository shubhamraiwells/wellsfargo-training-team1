package com.banking.teamone.repository;

import com.banking.teamone.model.Account;
import com.banking.teamone.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
   // Account findByIdAccount(Integer id);

}
