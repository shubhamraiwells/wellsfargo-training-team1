package com.banking.teamone.controller;


import com.banking.teamone.dto.CustomerIbRequestModel;
import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.service.AccountService;
import com.banking.teamone.service.CustomerIbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@RequestMapping("/api/test")
public class InternetBankingController {
    Logger logger = LoggerFactory.getLogger(InternetBankingController.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerIbService customerIbService;





    @GetMapping("/getIbAccountByUsername")
    @CrossOrigin
    public ResponseEntity<CustomerIb>getAccountIbByUsername(@RequestParam String username){
        try {
            CustomerIb customerIb = customerIbService.getCustomerByUsername(username);
            return new ResponseEntity<>(customerIb, HttpStatus.OK);
        }catch (Exception e){
            logger.info("Exception occured while get account ib by username: "+e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
    }

    @GetMapping("/getIbAccountByAccountNo")
    @CrossOrigin
    public ResponseEntity<CustomerIb>getAccountIbByAccountNo(@RequestParam String accountNo){
        try {
            CustomerIb customerIb = customerIbService.getCustomerByAccountNo(accountNo);
            return new ResponseEntity<>(customerIb, HttpStatus.OK);
        }catch (Exception e){
            logger.info("Exception occured while getting internet banking account by account no");
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
    }
}
