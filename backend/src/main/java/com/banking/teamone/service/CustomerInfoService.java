package com.banking.teamone.service;


import com.banking.teamone.repository.CustomerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoService {

    @Autowired
    CustomerInfoRepository customerInfoRepository;

    /**
     * Retrieves the email address associated with a customer by their ownerId.
     *
     * @param ownerId The unique identifier for the customer.
     * @return The email address of the customer if found, or null if not found.
     */
    public String getEmail(Integer ownerId){
        /** Check if a customer with the given ownerId exists and retrieve their email if available */
        return customerInfoRepository.findById(ownerId).isPresent()?customerInfoRepository.findById(ownerId).get().getEmailId():null;
    }
}
