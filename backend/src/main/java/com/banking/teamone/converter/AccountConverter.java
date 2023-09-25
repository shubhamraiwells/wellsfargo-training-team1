package com.banking.teamone.converter;

import com.banking.teamone.dto.AccountDto;
import com.banking.teamone.dto.PendingRequestModel;
import com.banking.teamone.model.Account;
import com.banking.teamone.model.CustomerInfo;
import org.springframework.beans.BeanUtils;
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

    public PendingRequestModel createPendingRequestModel(CustomerInfo customerInfo, String accountNo) {
        PendingRequestModel pendingRequestModel = new PendingRequestModel();
        BeanUtils.copyProperties(customerInfo, pendingRequestModel);
        pendingRequestModel.setAccountNo(accountNo);
        pendingRequestModel.setId(customerInfo.getId());
        return pendingRequestModel;
    }
}
