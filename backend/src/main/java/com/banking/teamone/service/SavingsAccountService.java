package com.banking.teamone.service;

import com.banking.teamone.converter.CustomerConverter;
import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.exception.DatabaseExceptions;
import com.banking.teamone.model.*;
import com.banking.teamone.model.Account;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.repository.CustomerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SavingsAccountService {
    @Autowired
    CustomerInfoRepository customerInfoRepository;

    @Autowired
    CustomerConverter customerConverter;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRequestService accountRequestService;


    /**
     * Update the balance of a savings account.
     *
     * @param accountNo The account number of the savings account to update.
     * @param toAdd     The amount to add to the account balance.
     * @return A message indicating the result of the update operation.
     */

    public String updateBalance(String accountNo,BigDecimal toAdd){
        Account fetchedAccount=accountService.getAccountById(accountNo);
        if(fetchedAccount!=null) {
            fetchedAccount.setTotalBalance(fetchedAccount.getTotalBalance().add(toAdd));
            accountService.createAccount(fetchedAccount);
            return "Account updated successfully";
        }
        return "Account not exist";
    }


    /**
     * Create a new savings account for a customer.
     *
     * @param customerInfoRequestModel The customer information for creating the account.
     * @return A message indicating the result of the account creation.
     */

    public String createSavingsAccount(CustomerInfoRequestModel customerInfoRequestModel){
        String accNo="aa";
        accNo = generateUniqueNo();
        CustomerInfo customerInfo = customerConverter.customerInfoRequestModelToCustomerInfo(customerInfoRequestModel);
        if(!checkInfo(customerInfo)) {
            throw new DatabaseExceptions.AadharCardRegisteredException();
        }
       CustomerInfo createdCust= customerInfoRepository.save(customerInfo);
       //CREATING ACCOUNT
        AccountRequest accountRequest = AccountRequest.builder()
                .id(accNo)
                .accountType(createdCust.getAccountType())
                .ownerId(createdCust.getId())
                .build();

        accountRequestService.createAccount(accountRequest);
        List<CustomerInfo> customerInfoList = new ArrayList<>();
//        System.out.println("Size of List:"+customerInfoList.size());
        customerInfoList = customerInfoRepository.findAll();
//        System.out.println("Size of List:"+customerInfoList.size());
        return "Account generated successfully";
    }

    /**
     * Generate a unique account number.
     *
     * @return A unique account number.
     */


    public String generateUniqueNo(){
        return UUID.randomUUID().toString().replace("-","");
    }
    /**
     * Check if customer information with the same Aadhar card number already exists.
     *
     * @param customerInfo The customer information to check.
     * @return True if the information is unique, false if a duplicate exists.
     */


    public Boolean checkInfo(CustomerInfo customerInfo){
        List<CustomerInfo> customerInfoList = customerInfoRepository.findAll();
        HashMap<String, Boolean> map= new HashMap<>();
        customerInfoList.stream().filter(x-> Objects.nonNull(x)).forEach(x-> map.put(x.getAadharCardNo(), Boolean.TRUE));
        return Objects.isNull(map.get(customerInfo.getAadharCardNo()));
    }

    /**
     * Retrieve customer information by ownerId.
     *
     * @param ownerId The unique identifier for the customer.
     * @return The customer information if found, or null if not found.
     */
    public CustomerInfo getAllCustomers(Integer ownerId){
        return customerInfoRepository.findById(ownerId).isPresent() ? customerInfoRepository.findById(ownerId).get():null;

    }

    /**
     * Retrieve customer information by first name.
     *
     * @param firstName The first name of the customer.
     * @return The customer information if found, or null if not found.
     */

    public CustomerInfo getCustomerByFirstName(String firstName){
        return customerInfoRepository.findByFirstName(firstName)!=null ?customerInfoRepository.findByFirstName(firstName):null;

    }

    /**
     * Retrieve all customers based on specific criteria.
     *
     * @return A list of customers matching the specified criteria.
     */
    public List<?> getAllCustomersBySpecificColumn(){

        return customerInfoRepository.findAllByColumn();
    }
}
