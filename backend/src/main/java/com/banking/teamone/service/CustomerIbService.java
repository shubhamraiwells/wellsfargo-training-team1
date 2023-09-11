package com.banking.teamone.service;

import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.repository.CustomerIbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerIbService {

    @Autowired
    private CustomerIbRepository customerIbRepository;

    public CustomerIb getCustomerByUsername(String username){
        return customerIbRepository.findById(username).isPresent() ?customerIbRepository.findById(username).get():null;
    }

    public CustomerIb getCustomerByAccountNo(String accountNo){
        return customerIbRepository.findByAccountNo(accountNo);
    }
    public void createCustomerIb(CustomerIb customerIb){

            customerIbRepository.save(customerIb);

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
