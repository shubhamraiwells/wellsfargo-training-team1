package com.banking.teamone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
        private BigDecimal transactionAmount;

}
