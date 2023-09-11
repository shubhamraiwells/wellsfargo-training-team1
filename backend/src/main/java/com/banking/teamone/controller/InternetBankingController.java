package com.banking.teamone.controller;


import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.service.AccountService;
import com.banking.teamone.service.CustomerIbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InternetBankingController {
Logger logger= LoggerFactory.getLogger(InternetBankingController.class);

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
           try {
               customerIbService.createCustomerIb(customerIb);
           }catch (Exception e){
               logger.error(e.getMessage());
               return new ResponseEntity<>("Something went wrong check your details",HttpStatus.BAD_REQUEST);
           }
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
    public ResponseEntity<CustomerIb>getAccountIbByAccountNo(@RequestParam String accountNo){
        CustomerIb customerIb=customerIbService.getCustomerByAccountNo(accountNo);
        return new ResponseEntity<>(customerIb,HttpStatus.OK);
    }


}
