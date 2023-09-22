package com.banking.teamone.service;

import com.banking.teamone.model.Admin;
import com.banking.teamone.model.CRole;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.repository.AdminRepository;
import com.banking.teamone.repository.CustomerIbRepository;
import com.banking.teamone.service.CustomerIbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerIbService customerIbService;

//    public boolean getAdmin(String username){
//        return adminRepository.findById(username).isPresent();
//    }

    public String createAdmin(String username,String password){
       CustomerIb customerIb= customerIbService.getCustomerByUsername(username);
        if(customerIb!=null){
            return "Username should be unique";
        }
        adminRepository.save(new Admin(username,password, CRole.ROLE_ADMIN));
        return "Admin registered successfully";
    }

    public Admin getAdminByUsername(String username){
        return adminRepository.findById(username).isPresent()?adminRepository.findById(username).get():null;

    }




}
