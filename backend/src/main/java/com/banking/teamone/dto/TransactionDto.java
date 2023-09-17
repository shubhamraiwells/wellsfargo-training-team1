package com.banking.teamone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDto {

    private String id;



    private String fromAccountNo;



    private String toAccountNo;


    private BigDecimal transactionAmount;


    private  LocalDateTime transactionDate= LocalDateTime.now();
}
