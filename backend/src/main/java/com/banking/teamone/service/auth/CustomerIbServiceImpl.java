package com.banking.teamone.service.auth;

import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.service.CustomerIbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class CustomerIbServiceImpl implements UserDetailsService {
    @Autowired
    CustomerIbService customerIbService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerIb customerIb= customerIbService.getCustomerByUsername(username);
        if(customerIb==null){
        throw new UsernameNotFoundException("user not found with this username");
        }

        return CustomerIbDetailsImpl.build(customerIb);

    }
}
