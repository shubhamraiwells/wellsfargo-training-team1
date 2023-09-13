package com.banking.teamone.controller;


import com.banking.teamone.dto.LoginRequestModel;
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
    public ResponseEntity<String>loginIbAccount(@RequestBody LoginRequestModel loginRequestModel){
    
    	CustomerIb customerIb =customerIbService.getCustomerByUsername(loginRequestModel.getUsername());
     
       if(customerIb==null){
           return new ResponseEntity<>("username not registered", HttpStatus.BAD_REQUEST);
       }else{
            if (!customerIb.getPassword().equals(loginRequestModel.getPassword()))  {
                return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("User logged in successfully", HttpStatus.ACCEPTED);
       }
    }
}


