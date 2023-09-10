package com.banking.teamone.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;


@Getter
@Entity
@Table(name="customerIb")
public class CustomerIb {
    @Id
    @Column(nullable = false,name = "username")
    private String username;

    @Column(nullable = false,name = "password")
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccount_no(Integer account_no) {
        this.accountNo = account_no;
    }

    @Column(name="acc_no",nullable = false)
    private Integer accountNo;
}
