package com.banking.teamone.controller;


import com.banking.teamone.dto.CustomerIbRequestModel;
import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.service.AccountService;
import com.banking.teamone.service.CustomerIbService;
import com.banking.teamone.service.SavingsAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SavingsAccountService savingsAccountService;
    @PostMapping(   "/fetchUsers")
    @CrossOrigin
    public ResponseEntity<List<?>>fetchAllUsers(){
        List<?> customerInfo1=savingsAccountService.getAllCustomersBySpecificColumn();
        return new ResponseEntity<>(customerInfo1,HttpStatus.OK);
    }
    @PostMapping(   "/search")
    @CrossOrigin
    public ResponseEntity<?>search(@RequestBody String firstName) {
        CustomerInfo customerInfo1 = savingsAccountService.getCustomerByFirstName(firstName);
        if (customerInfo1 == null) {
            return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(customerInfo1, HttpStatus.OK);
        }
    }
}
