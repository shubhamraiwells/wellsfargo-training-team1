package com.banking.teamone.controller;

import java.math.BigDecimal;

import com.banking.teamone.dto.AccountDto;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.dto.AccountDto;
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

@RestController
public class FetchBalanceController {
	

	  
    @Autowired
    private AccountService AccountService;
    @Autowired
    private CustomerIbService customerIbService;
    @PostMapping("/GetTotalBalance")
    @CrossOrigin
    public ResponseEntity<String>GetTotalBalance(@RequestBody BalanceDto balanceDto){
        CustomerIb customerIb =customerIbService.getCustomerByUsername(balanceDto.getUsername());
      Account account = AccountService.getAccountById(customerIb.getAccountNo());
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
        List<AccountDto> accountList = AccountService.fetchAccountByOwnerId(a);
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }





}
