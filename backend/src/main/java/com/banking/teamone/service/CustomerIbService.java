package com.banking.teamone.service;

import com.banking.teamone.converter.CustomerConverter;
import com.banking.teamone.dto.CustomerIbRequestModel;
import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.repository.CustomerIbRepository;
import com.banking.teamone.repository.CustomerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerIbService {

    @Autowired
    CustomerInfoRepository customerInfoRepository;
    @Autowired
    CustomerConverter customerConverter;


    @Autowired
    private CustomerIbRepository customerIbRepository;

    public CustomerIb getCustomerByUsername(String username){
        return customerIbRepository.findById(username).isPresent() ?customerIbRepository.findById(username).get():null;
    }

    public CustomerIb getCustomerByAccountNo(String accountNo){
        return customerIbRepository.findByAccountNo(accountNo);
    }
    public void createCustomerIb(CustomerIbRequestModel customerIbRequestModel){
        CustomerIb customerIb = customerConverter.customerIbRequestModelToCustomerIb(customerIbRequestModel);
        customerIbRepository.save(customerIb);

    }

    public String createSavingsAccount(CustomerInfoRequestModel customerInfoRequestModel){
        String accNo="aa";
        accNo = generateUniqueNo();
        CustomerInfo customerInfo = customerConverter.customerInfoRequestModelToCustomerInfo(customerInfoRequestModel);
        if(!checkInfo(customerInfo))
            return "An account with the given Aadhar Number already exists";
        customerInfoRepository.save(customerInfo);
        List<CustomerInfo> customerInfoList = new ArrayList<>();
        System.out.println("Size of List:"+customerInfoList.size());
        customerInfoList = customerInfoRepository.findAll();
        System.out.println("Size of List:"+customerInfoList.size());
        return accNo;
    }
    private String generateUniqueNo(){
        return UUID.randomUUID().toString().replace("-","");
    }
    private Boolean checkInfo(CustomerInfo customerInfo){
        List<CustomerInfo> customerInfoList = customerInfoRepository.findAll();
        HashMap<String, Boolean> map= new HashMap<>();
        customerInfoList.stream().filter(x-> Objects.nonNull(x)).forEach(x-> map.put(x.getAadharCardNo(), Boolean.TRUE));
        return Objects.isNull(map.get(customerInfo.getAadharCardNo()));
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
