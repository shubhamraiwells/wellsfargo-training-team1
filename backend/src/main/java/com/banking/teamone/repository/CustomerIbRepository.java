package com.banking.teamone.repository;

import com.banking.teamone.model.CustomerIb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerIbRepository extends JpaRepository<CustomerIb,String> {
     CustomerIb findByAccountNo(String accountNo);

     Optional<CustomerIb> findById(String username);




}
