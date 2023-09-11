package com.banking.teamone.controller;


import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.service.AccountService;
import com.banking.teamone.service.CustomerIbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InternetBankingController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerIbService customerIbService;



    @PostMapping("/createIbAccount")
    @CrossOrigin
    public ResponseEntity<String>createIbAccount(@RequestBody CustomerIb customerIb){
       CustomerIb customerIb1=customerIbService.getCustomerByUsername(customerIb.getUsername());
       CustomerIb customerIb2=customerIbService.getCustomerByAccountNo(customerIb.getAccountNo());
       if(customerIb1!=null || customerIb2!=null){
           return new ResponseEntity<>("username or account number already registered", HttpStatus.OK);
       }else{
           customerIbService.createCustomerIb(customerIb);
           return new ResponseEntity<>("User registered for Internet banking",HttpStatus.CREATED);
       }
    }

    @GetMapping("/getIbAccountByUsername")
    @CrossOrigin
    public ResponseEntity<CustomerIb>getAccountIbByUsername(@RequestParam String username){
        CustomerIb customerIb=customerIbService.getCustomerByUsername(username);
        return new ResponseEntity<>(customerIb,HttpStatus.OK);
    }

    @GetMapping("/getIbAccountByAccountNo")
    @CrossOrigin
    public ResponseEntity<CustomerIb>getAccountIbByAccountNo(@RequestParam Integer accountNo){
        CustomerIb customerIb=customerIbService.getCustomerByAccountNo(accountNo);
        return new ResponseEntity<>(customerIb,HttpStatus.OK);
    }


}
