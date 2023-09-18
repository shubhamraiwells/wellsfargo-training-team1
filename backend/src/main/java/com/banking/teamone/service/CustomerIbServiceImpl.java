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
        CustomerIb customerIb= customerIbService.getCustomerByUsername(username);
        Admin admin= adminService.getAdminByUsername(username);
        if(customerIb==null && admin==null){
        throw new UsernameNotFoundException("user not found with this username");
        }
        if(customerIb!=null && admin!=null){
            throw new UsernameNotFoundException("user not found or duplicate username");
        }

        if(customerIb!=null) {
            return CustomerIbDetailsImpl.build(customerIb);
        }
        System.out.println("admin returned in loaduserbynam");
        return AdminDetailImpl.build(admin);

        }


}
