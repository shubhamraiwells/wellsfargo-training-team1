package com.banking.teamone.service;


import com.banking.teamone.repository.CustomerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoService {

    @Autowired
    CustomerInfoRepository customerInfoRepository;


    public String getEmail(Integer ownerId){
        return customerInfoRepository.findById(ownerId).isPresent()?customerInfoRepository.findById(ownerId).get().getEmailId():null;
    }
}
