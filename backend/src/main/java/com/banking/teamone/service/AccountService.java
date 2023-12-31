package com.banking.teamone.service;


import com.banking.teamone.converter.AccountConverter;
import com.banking.teamone.dto.AccountDto;
import com.banking.teamone.model.Account;
import com.banking.teamone.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.stream.Collectors;

    @Service
   public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountConverter accountConverter;


    public Account getAccountById(String Id){
        Optional<Account> account = accountRepository.findById(Id);

        return account.orElse(null);
    }


    
    public  Account createAccount(Account obj){
        return accountRepository.save(obj);
    }

    public List<Account> getAllAccount(){
        return accountRepository.findAll();
    }


    //getting all account by owner id
    public List<AccountDto> fetchAccountByOwnerId(Integer ownerId){
        Collection<Account> accountList = accountRepository.findByOwnerId(ownerId);
        return accountList.stream().filter(Objects::nonNull).map(x-> accountConverter.AccountToAccountDto(x)).collect(Collectors.toList());
    }


}
