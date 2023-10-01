package com.banking.teamone.service;

import com.banking.teamone.converter.CustomerConverter;
import com.banking.teamone.dto.CustomerIbRequestModel;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.repository.CustomerIbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class CustomerIbService {
    // Constants for account lock and failed login attempts

    public static final int MAX_FAILED_ATTEMPTS = 3;

    public static final long LOCK_TIME_DURATION =  30000; // 30 seconds (for testing purposes)
    @Autowired
    CustomerConverter customerConverter;

    @Autowired
    private CustomerIbRepository customerIbRepository;

    // Retrieve a customer by their username
    public CustomerIb getCustomerByUsername(String username){
        return customerIbRepository.findById(username).isPresent() ?customerIbRepository.findById(username).get():null;
    }

    // Retrieve a customer by their account number
    public CustomerIb getCustomerByAccountNo(String accountNo){
        return customerIbRepository.findByAccountNo(accountNo);
    }

    // Create a new customer

    public CustomerIb createCustomerIb(CustomerIb customerIb){


        return customerIbRepository.save(new CustomerIb(customerIb.getUsername(),customerIb.getPassword(),customerIb.getRole(),customerIb.getAccountNo(),
                customerIb.getIsActive(),customerIb.isAccountNonLocked(),customerIb.getFailedAttempt(),customerIb.getLockTime()));
    }
    // Increase the failed login attempts for a customer

    public void increaseFailedAttempts(CustomerIb user) {
        int newFailAttempts = user.getFailedAttempt() + 1;
        customerIbRepository.updateFailedAttempts(newFailAttempts, user.getUsername());
    }
    // Reset the failed login attempts for a customer

    public void resetFailedAttempts(String username) {
        customerIbRepository.updateFailedAttempts(0, username);
    }
    // Lock a customer's account due to too many failed login attempts

    public void lock(CustomerIb user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());

        customerIbRepository.save(user);
    }
    // Unlock a customer's account when the lock time has expired

    @Transactional
    public boolean unlockWhenTimeExpired(CustomerIb user) {
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt(0);

            customerIbRepository.save(user);

            return true;
        }

        return false;
    }

}
