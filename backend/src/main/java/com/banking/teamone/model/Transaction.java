package com.banking.teamone.model;


import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="Transaction")
@Transactional
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Column(nullable = false)
    private String fromAccountNo;

    @Column(nullable = false)

    private String toAccountNo;
    @Column(nullable = false)

    private BigDecimal transactionAmount;

    @Column(nullable = false)
    private final LocalDateTime transactionDate= LocalDateTime.now();



}
