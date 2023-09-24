package com.banking.teamone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproveBankAccountModel {
    private String accountNo;
    private Boolean approveAccount;
}
