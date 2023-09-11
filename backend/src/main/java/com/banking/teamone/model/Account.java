package com.banking.teamone.model;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Entity
@Table(name = "account")
public class Account{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "acc_type", length = 50, nullable = false,columnDefinition = "varchar(50) default 'saving'")
    private String accountType;


    @Column(name = "acc_activation_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date accountActivationDate;

    @Column(name = "total_bal", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalBalance;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setAccountActivationDate(Date accountActivationDate) {
        this.accountActivationDate = accountActivationDate;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }






}