package com.banking.teamone.service;

import com.banking.teamone.model.Admin;
import com.banking.teamone.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    boolean getAdmin(String emailId){
        return adminRepository.findById(emailId).isPresent();
    }



}
