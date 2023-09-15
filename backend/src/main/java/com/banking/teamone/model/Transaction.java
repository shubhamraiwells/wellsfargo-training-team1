package com.banking.teamone.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="Transaction")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Column(nullable = false)
    private String fromAccountNo;

    @Column
    private String toAccountNo;

    @Column(nullable = false)
    private BigDecimal transactionAmount;

    @Column
    private final Date transactionDate= new Date();



}
