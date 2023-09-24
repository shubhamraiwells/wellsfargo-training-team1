package com.banking.teamone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApproveBankAccountModel {
    private String accountNo;
    private Boolean approveAccount;
}
