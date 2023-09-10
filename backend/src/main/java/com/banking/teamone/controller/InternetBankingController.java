package com.banking.teamone.controller;


import com.banking.teamone.dto.CustomerIbRequestModel;
import com.banking.teamone.model.CustomerIbModel;
import com.banking.teamone.service.AccountService;
import com.banking.teamone.service.CustomerIbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class InternetBankingController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerIbService customerIbService;



    @PostMapping("/createIbAccount")
    @CrossOrigin
    public ResponseEntity<String>createIbAccount(@Valid @RequestBody CustomerIbRequestModel customerIbRequestModel){
       CustomerIbModel customerIbModel1 = customerIbService.getCustomerByUsername(customerIbRequestModel.getUsername());
       CustomerIbModel customerIbModel2 =customerIbService.getCustomerByAccountNo(customerIbRequestModel.getAccountNo());
       if(customerIbModel1 !=null || customerIbModel2 !=null){
           return new ResponseEntity<>("username or account number already registered", HttpStatus.OK);
       }else{
           customerIbService.createCustomerIb(customerIbRequestModel);
           return new ResponseEntity<>("User registered for Internet banking",HttpStatus.CREATED);
       }
    }

    @GetMapping("/getIbAccountByUsername")
    @CrossOrigin
    public ResponseEntity<CustomerIbModel> getIbAccountByUsername(@Valid @RequestParam String username){
        CustomerIbModel customerIbModel =customerIbService.getCustomerByUsername(username);
        return new ResponseEntity<>(customerIbModel,HttpStatus.OK);
    }

    @GetMapping("/getIbAccountByAccountNo")
    @CrossOrigin
    public ResponseEntity<CustomerIbModel>getIbAccountByAccountNo(@Valid @RequestParam Integer accountNo){
        CustomerIbModel customerIbModel =customerIbService.getCustomerByAccountNo(accountNo);
        return new ResponseEntity<>(customerIbModel,HttpStatus.OK);
    }


}
