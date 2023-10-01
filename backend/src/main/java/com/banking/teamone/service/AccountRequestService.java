package com.banking.teamone.service;

import com.banking.teamone.converter.AccountConverter;
import com.banking.teamone.dto.PendingRequestModel;
import com.banking.teamone.model.AccountRequest;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.repository.AccountRequestRepository;
import com.banking.teamone.repository.CustomerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountRequestService {

    @Autowired
    private AccountRequestRepository accountRequestRepository;

    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Autowired
    private AccountConverter accountConverter;

    public AccountRequest createAccount(AccountRequest object){
        return accountRequestRepository.save(object);
    }


    //method to get all pending account activation requests for customer internet banking
    public List<PendingRequestModel> getAllPendingRequests() {
        List<AccountRequest> accountRequestList = accountRequestRepository.findAll();
        List<PendingRequestModel> pendingRequestModelList= new ArrayList<PendingRequestModel>();
        //iterating over all request in our list
        for(AccountRequest account : accountRequestList) {
            Integer id = account.getOwnerId();
            //checking if customerinfo exists or not
            CustomerInfo customerInfo = customerInfoRepository.findById(id).orElse(null);
            if(customerInfo!=null) {
                pendingRequestModelList.add(accountConverter.createPendingRequestModel(customerInfo, account.getId()));
            }
            }
        return pendingRequestModelList;
    }
}
