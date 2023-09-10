package com.banking.teamone.controller;

import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.banking.teamone.model.UserModel;
import com.banking.teamone.repository.UserRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class BankController {
//    @Autowired
//    private UserRepository userRepository;
    @Autowired
    private BankService bankService;

//    @GetMapping(value="/users")
//    public Iterable<UserModel> setUser() {
//        UserModel userModel = new UserModel("Sanjay",38);
//        userModel.getName();
//        return this.userRepository.findAll();
////        this.userRepository.save(userModel);
////        return userModel;
//    }

    @PostMapping(value="/savings_account_signup")
    public String createSavingsAccount(@Valid @RequestBody CustomerInfoRequestModel customerInfoRequestModel){

        return bankService.createSavingsAccount(customerInfoRequestModel);

    }
}
