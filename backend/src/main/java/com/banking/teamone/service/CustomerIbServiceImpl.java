package com.banking.teamone.service;

import com.banking.teamone.model.Admin;
import com.banking.teamone.model.CustomerIb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomerIbServiceImpl implements UserDetailsService {
    @Autowired
    CustomerIbService customerIbService;
    @Autowired
    AdminService adminService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Attempt to retrieve a CustomerIb by the provided username
        CustomerIb customerIb= customerIbService.getCustomerByUsername(username);
        // Attempt to retrieve an Admin by the provided username

        Admin admin= adminService.getAdminByUsername(username);
        // Check if neither a CustomerIb nor an Admin is found with the provided username

        if(customerIb==null && admin==null){
        throw new UsernameNotFoundException("user not found with this username");
        }
        // Check if both a CustomerIb and an Admin exist with the same username (duplicate username)

        if(customerIb!=null && admin!=null){
            throw new UsernameNotFoundException("user not found or duplicate username");
        }
        // If a CustomerIb is found, return a UserDetails object using CustomerIbDetailsImpl

        if(customerIb!=null) {
            return CustomerIbDetailsImpl.build(customerIb);
        }
        // If an Admin is found, return a UserDetails object using AdminDetailImpl

        return AdminDetailImpl.build(admin);

        }


}
