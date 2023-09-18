package com.banking.teamone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@Transactional
@Getter
@Setter
public class AccountOwner {

    @Id
    private String  acc_no;

    @Column(name="cust_id",nullable = false)
    private Integer cust_id;

    @Column(name = "acc_type", length = 50, nullable = false,columnDefinition = "varchar(50) default 'saving'")
    private String accountType;

    @Column(name = "total_bal", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalBalance;
}
