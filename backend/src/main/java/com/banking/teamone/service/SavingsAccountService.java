package com.banking.teamone.service;

import com.banking.teamone.converter.CustomerConverter;
import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.repository.CustomerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SavingsAccountService {
    @Autowired
    CustomerInfoRepository customerInfoRepository;

    @Autowired
    CustomerConverter customerConverter;

    public String createSavingsAccount(CustomerInfoRequestModel customerInfoRequestModel){
        String accNo="aa";
        accNo = generateUniqueNo();
        CustomerInfo customerInfo = customerConverter.customerInfoRequestModelToCustomerInfo(customerInfoRequestModel);
        if(!checkInfo(customerInfo))
            return "An account with the given Aadhar Number already exists";
        customerInfoRepository.save(customerInfo);
        List<CustomerInfo> customerInfoList = new ArrayList<>();
        System.out.println("Size of List:"+customerInfoList.size());
        customerInfoList = customerInfoRepository.findAll();
        System.out.println("Size of List:"+customerInfoList.size());
        return accNo;
    }
    private String generateUniqueNo(){
        return UUID.randomUUID().toString().replace("-","");
    }
    private Boolean checkInfo(CustomerInfo customerInfo){
        List<CustomerInfo> customerInfoList = customerInfoRepository.findAll();
        HashMap<String, Boolean> map= new HashMap<>();
        customerInfoList.stream().filter(x-> Objects.nonNull(x)).forEach(x-> map.put(x.getAadharCardNo(), Boolean.TRUE));
        return Objects.isNull(map.get(customerInfo.getAadharCardNo()));
    }

}
