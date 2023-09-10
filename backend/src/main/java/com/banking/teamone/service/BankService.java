package com.banking.teamone.service;

import com.banking.teamone.converter.CustInfoReqToCustInfo;
import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.repository.CustomerInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BankService {
    @Autowired
    CustomerInfoRepository customerInfoRepository;
    @Autowired
    CustInfoReqToCustInfo custInfoReqToCustInfo;
    public String createSavingsAccount(CustomerInfoRequestModel customerInfoRequestModel){
        String accNo="aa";
        accNo = generateUniqueNo();
        CustomerInfo customerInfo = custInfoReqToCustInfo.toCustomerInfo(customerInfoRequestModel);
        customerInfoRepository.save(customerInfo);
        List<CustomerInfo> customerInfoList = new ArrayList<>();
       System.out.println("Size of List:"+customerInfoList.size());
        customerInfoList = customerInfoRepository.findAll();
        System.out.println("Size of List:"+customerInfoList.size());
        return accNo;
    }
    private String generateUniqueNo(){
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid;
    }
}
