package com.banking.teamone.model;

import lombok.*;

import javax.persistence.*;



@Getter
@Setter
@Data
@Entity
@Builder
@Table(name="customerIb")
public class CustomerIbModel {
    @Id
    @Column(nullable = false,name = "username")
    private String username;

    @Column(nullable = false,name = "password")
    private String password;

    @Column(name="acc_no",nullable = false)
    private Integer accountNo;

//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setAccount_no(Integer account_no) {
//        this.accountNo = account_no;
//    }
}
