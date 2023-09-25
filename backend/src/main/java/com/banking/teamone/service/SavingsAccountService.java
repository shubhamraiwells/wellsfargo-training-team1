package com.banking.teamone.service;

import com.banking.teamone.converter.CustomerConverter;
import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.model.*;
import com.banking.teamone.model.Account;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.repository.CustomerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SavingsAccountService {
    @Autowired
    CustomerInfoRepository customerInfoRepository;

    @Autowired
    CustomerConverter customerConverter;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRequestService accountRequestService;

    public String updateBalance(String accountNo,BigDecimal toAdd){
        Account fetchedAccount=accountService.getAccountById(accountNo);
        if(fetchedAccount!=null) {
            fetchedAccount.setTotalBalance(fetchedAccount.getTotalBalance().add(toAdd));
            accountService.createAccount(fetchedAccount);
            return "Account updated successfully";
        }
        return "Account not exist";
    }


    public String createSavingsAccount(CustomerInfoRequestModel customerInfoRequestModel){
        String accNo="aa";
        accNo = generateUniqueNo();
        CustomerInfo customerInfo = customerConverter.customerInfoRequestModelToCustomerInfo(customerInfoRequestModel);
        if(!checkInfo(customerInfo))
            return "An account with the given Aadhar Number already exists";
        CustomerInfo createdCust= customerInfoRepository.save(customerInfo);

        //CREATING ACCOUNT
        AccountRequest accountRequest = AccountRequest.builder()
                .id(accNo)
                .accountType(createdCust.getAccountType())
                .ownerId(createdCust.getId())
                .build();

        accountRequestService.createAccount(accountRequest);
        List<CustomerInfo> customerInfoList = new ArrayList<>();
        System.out.println("Size of List:"+customerInfoList.size());
        customerInfoList = customerInfoRepository.findAll();
        System.out.println("Size of List:"+customerInfoList.size());
        return "Account generated successfully";
    }
    public String generateUniqueNo(){
        return UUID.randomUUID().toString().replace("-","");
    }
    public Boolean checkInfo(CustomerInfo customerInfo){
        List<CustomerInfo> customerInfoList = customerInfoRepository.findAll();
        HashMap<String, Boolean> map= new HashMap<>();
        customerInfoList.stream().filter(x-> Objects.nonNull(x)).forEach(x-> map.put(x.getAadharCardNo(), Boolean.TRUE));
        return Objects.isNull(map.get(customerInfo.getAadharCardNo()));
    }

    public CustomerInfo getAllCustomers(Integer ownerId){
        return customerInfoRepository.findById(ownerId).isPresent() ? customerInfoRepository.findById(ownerId).get():null;

    }

    public CustomerInfo getCustomerByFirstName(String firstName){
        return customerInfoRepository.findByFirstName(firstName)!=null ?customerInfoRepository.findByFirstName(firstName):null;

    }

    public List<?> getAllCustomersBySpecificColumn(){

        return customerInfoRepository.findAllByColumn();
    }
}
