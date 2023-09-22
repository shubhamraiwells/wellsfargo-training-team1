package com.banking.teamone.repository;

import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.model.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo,Integer> {
    @Query("select id,firstName from CustomerInfo")
    List<?> findAllByColumn();

    @Query("select e from CustomerInfo e where e.firstName = :firstName")
    CustomerInfo findByFirstName(@Param("firstName") String firstName);
}
