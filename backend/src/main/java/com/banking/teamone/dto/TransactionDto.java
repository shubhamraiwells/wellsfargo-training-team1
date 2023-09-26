package com.banking.teamone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDto {

    private Integer id;



    private String fromAccountNo;



    private String toAccountNo;


    private BigDecimal transactionAmount;


    private Date transactionDate= new Date();

    @Override
    public boolean equals(Object o){
        if(o==this){
            return true;
        }
        if(!(o instanceof TransactionDto)){
            return false;
        }
        return ((fromAccountNo.compareTo(((TransactionDto) o).fromAccountNo)==0)
                && (toAccountNo.compareTo(((TransactionDto) o).toAccountNo)==0));
    }
}
