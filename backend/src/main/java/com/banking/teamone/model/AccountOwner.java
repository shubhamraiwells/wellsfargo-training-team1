package com.banking.teamone.model;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@AllArgsConstructor
public class AccountOwner {

    @Id
    private String  acc_no;

    @Column(name="cust_id",nullable = false)
    private Integer cust_id;

}
