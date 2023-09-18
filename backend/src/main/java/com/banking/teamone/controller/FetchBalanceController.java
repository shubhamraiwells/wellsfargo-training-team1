package com.banking.teamone.controller;

import java.math.BigDecimal;

import com.banking.teamone.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.banking.teamone.model.Account;
import com.banking.teamone.dto.BalanceDto;
import com.banking.teamone.service.AccountService;
import java.util.List;

@RestController
public class FetchBalanceController {
	

	  
    @Autowired
    private AccountService AccountService;

    @PostMapping("/GetTotalBalance")
    @CrossOrigin
    public ResponseEntity<String>GetTotalBalance(@RequestBody BalanceDto balanceDto){
        Account account =AccountService.getAccountById(balanceDto.getAcc_no());
       if(account==null){
           return new ResponseEntity<>("Account does not exist, Please check your account number and try again!", HttpStatus.BAD_REQUEST);
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
