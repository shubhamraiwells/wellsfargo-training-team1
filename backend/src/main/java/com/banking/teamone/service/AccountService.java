package com.banking.teamone.service;


import com.banking.teamone.converter.AccountConverter;
import com.banking.teamone.dto.AccountDto;
import com.banking.teamone.model.Account;
import com.banking.teamone.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountConverter accountConverter;
   

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

  public List<AccountDto> fetchAccountByOwnerId(Integer ownerId){
        Collection<Account> accountList = accountRepository.findByOwnerId(ownerId);
        List<AccountDto> accountDtoList = accountList.stream().filter(x-> Objects.nonNull(x)).map( x-> accountConverter.AccountToAccountDto(x)).collect(Collectors.toList());
        return accountDtoList;
    }

}
