package com.banking.teamone.dto;

import com.banking.teamone.model.TransType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {

 @NotBlank
 @NotNull
        private String fromAccountNo;


 @NotBlank
 @NotNull

        private String toAccountNo;

@NotBlank
@NotNull
@Positive(message = "transaction amount should be positive")
        private BigDecimal transactionAmount;


TransType transType;

}
