package com.banking.teamone.controller;

import java.math.BigDecimal;

import com.banking.teamone.dto.AccountDto;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.dto.AccountDto;
import com.banking.teamone.security.AuthTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.banking.teamone.model.Account;
import com.banking.teamone.dto.BalanceDto;
import com.banking.teamone.service.AccountService;
import com.banking.teamone.service.CustomerIbService;
import java.util.List;
import java.util.List;
import java.util.Map;

@RestController
public class FetchBalanceController {

    private static final Logger logger= LoggerFactory.getLogger(AuthTokenFilter.class);

	  
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerIbService customerIbService;
    @PostMapping("/GetTotalBalance")
    @CrossOrigin
    public ResponseEntity<String>GetTotalBalance(@RequestBody Map<String,String> data){
        CustomerIb customerIb =customerIbService.getCustomerByUsername(data.get("username"));
      Account account = accountService.getAccountById(customerIb.getAccountNo());
//      System.out.println(customerIb.getAccountNo());
//      System.out.println(account.toString());
       if(account==null){
           return new ResponseEntity<>("Account not created on this username, Please check your username and try again!", HttpStatus.BAD_REQUEST);
       }else{
           BigDecimal val;
          val = account.getTotalBalance();
           return new ResponseEntity<>(val.toString(), HttpStatus.ACCEPTED);
       }
    }
    @GetMapping("/LoadUserDetails/{ownerId}")
    @CrossOrigin
    public ResponseEntity<List<AccountDto>>getAccountDetails(@PathVariable("ownerId") String ownerId){
        Integer a = Integer.parseInt(ownerId);
        List<AccountDto> accountList = accountService.fetchAccountByOwnerId(a);
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }





}
