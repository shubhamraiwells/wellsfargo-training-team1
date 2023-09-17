package com.banking.teamone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
public class Account{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //id refers account number
    private String id;
    private String id;

    @Column(name = "acc_type", length = 50, nullable = false,columnDefinition = "varchar(50) default 'saving'")
    private String accountType;


    @Column
    private Integer ownerId;


    @Column
    private Boolean isActive=true;

    @Column(name = "acc_activation_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date accountActivationDate;

    @Column(name = "total_bal", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalBalance;


    public void setId(String id) {
    public void setId(String id) {
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