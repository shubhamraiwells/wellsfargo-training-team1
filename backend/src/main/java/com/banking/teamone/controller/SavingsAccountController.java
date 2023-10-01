package com.banking.teamone.controller;

import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.model.Account;
import com.banking.teamone.security.AuthTokenFilter;
import com.banking.teamone.service.SavingsAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/savingAccount")
public class SavingsAccountController {

    private static final Logger logger= LoggerFactory.getLogger(SavingsAccountController.class);


    @Autowired
    private SavingsAccountService savingsAccountService;
    @PostMapping(value="/createSavingsAccount")
    @CrossOrigin
    public String createSavingsAccount(@Valid @RequestBody CustomerInfoRequestModel customerInfoRequestModel){
        try {
            return savingsAccountService.createSavingsAccount(customerInfoRequestModel);
        }catch(Exception e){
            logger.info("Exception in creating saving account: "+e.getMessage());
            return  null;
        }
    }

    @PostMapping(value="/updateBalance")
    @CrossOrigin
    public String updateBalance(@Valid @RequestBody Map<String,String> newamount){
        try{
            String accountNo = newamount.get("accountNo");
            BigDecimal toadd = new BigDecimal(newamount.get("toAdd"));

            return savingsAccountService.updateBalance(accountNo,toadd);

        }catch(Exception e){
            logger.info("Exception occured in updating balance: "+e.getMessage());
            return "Some Exception occured in updating balance";
        }
    }
}