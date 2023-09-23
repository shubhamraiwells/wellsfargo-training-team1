package com.banking.teamone.repository;

import com.banking.teamone.model.CustomerIb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CustomerIbRepository extends JpaRepository<CustomerIb,String> {
     CustomerIb findByAccountNo(String accountNo);

     Optional<CustomerIb> findById(String username);


     @Query("UPDATE CustomerIb u SET u.failedAttempt = ?1 WHERE u.username = ?2")
     @Modifying
     public void updateFailedAttempts(int failAttempts, String username);




}
