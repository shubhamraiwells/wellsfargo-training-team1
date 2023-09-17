package com.banking.teamone.service;


import com.banking.teamone.model.Account;
import com.banking.teamone.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
   

    public Account getAccountById(String Id){
        Optional<Account> account = accountRepository.findById(Id);

        return account.isPresent()?account.get():null;
    }


    
    public  Account createAccount(Account obj){
        return accountRepository.save(obj);
    }
    public List<Account> getAllAccount(){
        return accountRepository.findAll();
    }

}
