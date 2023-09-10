package com.banking.teamone.converter;

import com.banking.teamone.dto.CustomerIbRequestModel;
import com.banking.teamone.model.CustomerIbModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    public CustomerIbModel customerIdRequestModelToCustomerIdModel(CustomerIbRequestModel customerIbRequestModel) {
        return CustomerIbModel.builder()
                .accountNo(customerIbRequestModel.getAccountNo())
                .password(customerIbRequestModel.getPassword())
                .username(customerIbRequestModel.getUsername())
                .build();
    }
}
