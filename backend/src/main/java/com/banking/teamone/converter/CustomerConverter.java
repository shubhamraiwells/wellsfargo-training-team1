package com.banking.teamone.converter;

import com.banking.teamone.dto.CustomerIbRequestModel;
import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.model.CustomerInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    public CustomerInfo customerInfoRequestModelToCustomerInfo(CustomerInfoRequestModel customerInfoRequestModel){
        CustomerInfo customerInfo = new CustomerInfo();
        BeanUtils.copyProperties(customerInfoRequestModel, customerInfo);
        return customerInfo;
    }

    public CustomerIb customerIbRequestModelToCustomerIb(CustomerIbRequestModel customerIbRequestModel) {
        CustomerIb customerIb = new CustomerIb();
        BeanUtils.copyProperties(customerIbRequestModel, customerIb);
        return customerIb;
    }
}
