package com.banking.teamone.controller;

import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.model.Account;
import com.banking.teamone.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/savingAccount")
public class SavingsAccountController {

    @Autowired
    private SavingsAccountService savingsAccountService;
    @PostMapping(value="/createSavingsAccount")
    @CrossOrigin
    public String createSavingsAccount(@Valid @RequestBody CustomerInfoRequestModel customerInfoRequestModel){
        return savingsAccountService.createSavingsAccount(customerInfoRequestModel);
    }

    @PostMapping(value="/updateBalance")
    @CrossOrigin
    public String updateBalance(@Valid @RequestBody Map<String,String> newamount){
        try{
            String accountNo = newamount.get("accountNo");
            BigDecimal toadd = new BigDecimal(newamount.get("toAdd"));

            return savingsAccountService.updateBalance(accountNo,toadd);

        }catch(Exception e){
            return "Some Exception occured in updating balance";
        }
    }
}