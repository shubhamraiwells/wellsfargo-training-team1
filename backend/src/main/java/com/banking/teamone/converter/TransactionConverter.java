package com.banking.teamone.converter;

import com.banking.teamone.dto.CustomerIbRequestModel;
import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.dto.TransactionRequestDto;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.model.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    public Transaction transactionRequestToTransaction(TransactionRequestDto transactionRequestDto){
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionRequestDto, transaction);
        return transaction;
    }

//    public CustomerIb customerIbRequestModelToCustomerIb(CustomerIbRequestModel customerIbRequestModel) {
//        CustomerIb customerIb = new CustomerIb();
//        BeanUtils.copyProperties(customerIbRequestModel, customerIb);
//        return customerIb;
//    }
}
