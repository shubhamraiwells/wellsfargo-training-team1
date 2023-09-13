package com.banking.teamone.controller;

import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SavingsAccountController {

    @Autowired
    private SavingsAccountService savingsAccountService;
    @PostMapping(value="/createSavingsAccount")
    @CrossOrigin
    public String createSavingsAccount(@Valid @RequestBody CustomerInfoRequestModel customerInfoRequestModel){
        return savingsAccountService.createSavingsAccount(customerInfoRequestModel);
    }
}