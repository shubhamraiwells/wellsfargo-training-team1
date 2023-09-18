package com.banking.teamone.converter;

import com.banking.teamone.dto.AccountDto;
import com.banking.teamone.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {
    public AccountDto AccountToAccountDto(Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNo(account.getId());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setOwnerId(account.getOwnerId());
        return accountDto;
    }
}
