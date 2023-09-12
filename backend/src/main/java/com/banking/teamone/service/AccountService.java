package com.banking.teamone.service;

import com.banking.teamone.model.Account;
import com.banking.teamone.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Optional<Account> getAccountById(String Id){
        return accountRepository.findById(Id);
    }
    public  Account createAccount(Account obj){
        return accountRepository.save(obj);
    }


}
