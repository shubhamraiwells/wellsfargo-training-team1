package com.banking.teamone.controller;


import com.banking.teamone.DTO.LoginDTO;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.service.CustomerIbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
Logger logger= LoggerFactory.getLogger(LoginController.class);

  
    @Autowired
    private CustomerIbService customerIbService;



    @PostMapping("/loginIbAccount")
    @CrossOrigin
    public ResponseEntity<String>loginIbAccount(@RequestBody LoginDTO loginDTO1){
    
    	CustomerIb customerIb =customerIbService.getCustomerByUsername(loginDTO1.getUsername());
     
       if(customerIb==null){
           return new ResponseEntity<>("username not registered", HttpStatus.OK);
       }else{
           
           return new ResponseEntity<>("User logged in",HttpStatus.CREATED);
       }
    }


    }


