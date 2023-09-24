package com.banking.teamone.service;

import com.banking.teamone.converter.CustomerConverter;
import com.banking.teamone.dto.CustomerIbRequestModel;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.repository.CustomerIbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerIbService {

    @Autowired
    CustomerConverter customerConverter;

    @Autowired
    private CustomerIbRepository customerIbRepository;

    public CustomerIb getCustomerByUsername(String username){
        return customerIbRepository.findById(username).isPresent() ?customerIbRepository.findById(username).get():null;
    }

    public CustomerIb getCustomerByAccountNo(String accountNo){
        return customerIbRepository.findByAccountNo(accountNo);
    }


    public CustomerIb createCustomerIb(CustomerIb customerIb){
        return customerIbRepository.save(customerIb);
    }


}
