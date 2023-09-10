package com.banking.teamone.service;

import com.banking.teamone.converter.CustomerConverter;
import com.banking.teamone.dto.CustomerIbRequestModel;
import com.banking.teamone.model.CustomerIbModel;
import com.banking.teamone.repository.CustomerIbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerIbService {

    @Autowired
    private CustomerIbRepository customerIbRepository;

    @Autowired
    private CustomerConverter customerConverter;

    public CustomerIbModel getCustomerByUsername(String username){
        return customerIbRepository.findById(username).isPresent() ?customerIbRepository.findById(username).get():null;
    }

    public CustomerIbModel getCustomerByAccountNo(Integer accountNo){
        return customerIbRepository.findByAccountNo(accountNo);
    }
    public void createCustomerIb(CustomerIbRequestModel customerIbRequestModel){
        CustomerIbModel customerIbModel = customerConverter.customerIdRequestModelToCustomerIdModel(customerIbRequestModel);
        try {
            customerIbRepository.save(customerIbModel);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

//    private Boolean checkIfAccountAlreadyLinked(Integer accountNo,String username){
//        CustomerIb customerIb=null;
//        if(
//                customerIbRepository.findById(username).isPresent()){
//            customerIb=customerIbRepository.findById(username).get();
//
//
//        }
//        if(customerIb!=null){
//            return (customerIb.getAccountListIb().stream().filter(e-> Objects.equals(e.getId(), accountNo)).toArray().length>0);
//        }
//        return false;
//    }
//
//    public CustomerIb createCustomerIb(CustomerIb customerIb,Integer accNo){
//        Boolean checkUser=checkIfAccountAlreadyLinked(accNo,customerIb.getUsername());
//        if(!checkUser) {
//            List<Account>accountList=customerIb.getAccountListIb();
//
//            return customerIbRepository.save(customerIb);
//        }else{
//            return null;
//        }
//    }
}
