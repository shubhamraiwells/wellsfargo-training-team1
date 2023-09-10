package com.banking.teamone.repository;

import com.banking.teamone.model.CustomerIbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerIbRepository extends JpaRepository<CustomerIbModel,String> {
     CustomerIbModel findByAccountNo(Integer accountNo);
}
