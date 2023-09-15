package com.banking.teamone.controller.admin;


import com.banking.teamone.repository.AccountRepository;
import com.banking.teamone.repository.CustomerIbRepository;
import com.banking.teamone.repository.CustomerInfoRepository;
import com.banking.teamone.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    @Autowired
    private CustomerIbRepository customerIbRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerInfoRepository customerInfoRepository;





}
