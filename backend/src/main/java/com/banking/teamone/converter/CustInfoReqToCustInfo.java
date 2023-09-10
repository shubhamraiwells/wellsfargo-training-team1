package com.banking.teamone.converter;

import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.model.CustomerInfo;
import org.springframework.stereotype.Component;

@Component
public class CustInfoReqToCustInfo {
    public CustomerInfo toCustomerInfo(CustomerInfoRequestModel customerInfoRequestModel){
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setTitle(customerInfoRequestModel.getTitle());
        customerInfo.setFirst_name(customerInfoRequestModel.getFirst_name());
        customerInfo.setMiddle_name(customerInfoRequestModel.getMiddle_name());
        customerInfo.setLast_name(customerInfoRequestModel.getLast_name());
        customerInfo.setFather_name(customerInfoRequestModel.getFather_name());
        customerInfo.setMobile_no(customerInfoRequestModel.getMobile_no());
        customerInfo.setEmail_id(customerInfoRequestModel.getEmail_id());
        customerInfo.setAadhar_card_no(customerInfoRequestModel.getAadhar_card_no());
        customerInfo.setDate_of_birth(customerInfoRequestModel.getDate_of_birth());
        customerInfo.setResidential_address_line_1(customerInfoRequestModel.getResidential_address_line_1());
        customerInfo.setResidential_address_line_2(customerInfoRequestModel.getResidential_address_line_2());
        customerInfo.setResidential_landmark(customerInfoRequestModel.getResidential_landmark());
        customerInfo.setResidential_city(customerInfoRequestModel.getResidential_city());
        customerInfo.setResidential_pincode(customerInfoRequestModel.getResidential_pincode());
        customerInfo.setPermanent_address_line_1(customerInfoRequestModel.getPermanent_address_line_1());
        customerInfo.setPermanent_address_line_2(customerInfoRequestModel.getPermanent_address_line_2());
        customerInfo.setPermanent_landmark(customerInfoRequestModel.getPermanent_landmark());
        customerInfo.setPermanent_city(customerInfoRequestModel.getPermanent_city());
        customerInfo.setPermanent_pincode(customerInfoRequestModel.getPermanent_pincode());
        customerInfo.setOccupation_type(customerInfoRequestModel.getOccupation_type());
        customerInfo.setSource_of_income(customerInfoRequestModel.getSource_of_income());
        customerInfo.setGross_annual_income(customerInfoRequestModel.getGross_annual_income());
        return customerInfo;
    }
}
