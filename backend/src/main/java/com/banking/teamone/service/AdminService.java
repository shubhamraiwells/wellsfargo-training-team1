package com.banking.teamone.service;

import com.banking.teamone.dto.ApproveBankAccountModel;
import com.banking.teamone.model.*;
import com.banking.teamone.repository.*;
import com.banking.teamone.service.CustomerIbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;


@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerIbService customerIbService;

    @Autowired
    private AccountRequestRepository accountRequestRepository;

    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Autowired
    private AccountRepository accountRepository;



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


    public String approveBankAccount(ApproveBankAccountModel approveBankAccountModel) {
        try {
            String accountNo = approveBankAccountModel.getAccountNo();
            Boolean approvalRequest = approveBankAccountModel.getApproveAccount();
            AccountRequest accountRequest = accountRequestRepository.findById(accountNo).orElse(null);
            assert accountRequest != null;
            CustomerInfo customer = customerInfoRepository.findById(accountRequest.getOwnerId()).orElse(null);
            assert customer != null;

            if (approvalRequest) {
                Account account = Account.builder()
                        .id(accountNo)
                        .accountType(customer.getAccountType())
                        .ownerId(customer.getId())
                        .accountActivationDate(new Date())
                        .isActive(true)
                        .totalBalance(BigDecimal.ZERO)
                        .build();

                accountRequestRepository.delete(accountRequest);
                accountRepository.save(account);
                return ("Bank account approved successfully");
            } else {
                return rejectBankAccount(accountRequest, customer);
            }
        } catch (Exception ex) {
            return "Some error occurred while approving bank account";
        }
    }
    public String rejectBankAccount(AccountRequest accountRequest, CustomerInfo customer) {
        try {
            accountRequestRepository.delete(accountRequest);
            customerInfoRepository.delete(customer);
            return "Bank account rejected successfully";
        } catch (Exception ex) {
            return "Some error occurred while rejecting bank account";
        }
    }
}
