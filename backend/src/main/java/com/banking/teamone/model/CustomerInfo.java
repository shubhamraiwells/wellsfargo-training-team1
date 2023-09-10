package com.banking.teamone.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "customer_info")
public class CustomerInfo {
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public void setAadhar_card_no(String aadhar_card_no) {
        this.aadhar_card_no = aadhar_card_no;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setResidential_address_line_1(String residential_address_line_1) {
        this.residential_address_line_1 = residential_address_line_1;
    }

    public void setResidential_address_line_2(String residential_address_line_2) {
        this.residential_address_line_2 = residential_address_line_2;
    }

    public void setResidential_landmark(String residential_landmark) {
        this.residential_landmark = residential_landmark;
    }

    public void setResidential_city(String residential_city) {
        this.residential_city = residential_city;
    }

    public void setResidential_pincode(String residential_pincode) {
        this.residential_pincode = residential_pincode;
    }

    public void setPermanent_address_line_1(String permanent_address_line_1) {
        this.permanent_address_line_1 = permanent_address_line_1;
    }

    public void setPermanent_address_line_2(String permanent_address_line_2) {
        this.permanent_address_line_2 = permanent_address_line_2;
    }

    public void setPermanent_landmark(String permanent_landmark) {
        this.permanent_landmark = permanent_landmark;
    }

    public void setPermanent_city(String permanent_city) {
        this.permanent_city = permanent_city;
    }

    public void setPermanent_pincode(String permanent_pincode) {
        this.permanent_pincode = permanent_pincode;
    }

    public void setOccupation_type(String occupation_type) {
        this.occupation_type = occupation_type;
    }

    public void setSource_of_income(String source_of_income) {
        this.source_of_income = source_of_income;
    }

    public void setGross_annual_income(Double gross_annual_income) {
        this.gross_annual_income = gross_annual_income;
    }

    private String first_name;
    private String middle_name;
    private String last_name;
    private String father_name;
    private String mobile_no;
    private String email_id;
    @Id
    private String aadhar_card_no;
    private Date date_of_birth;
    private String residential_address_line_1;
    private String residential_address_line_2;
    private String residential_landmark;
    private String residential_city;
    private String residential_pincode;
    private String permanent_address_line_1;
    private String permanent_address_line_2;
    private String permanent_landmark;
    private String permanent_city;
    private String permanent_pincode;
    private String occupation_type;
    private String source_of_income;
    private Double gross_annual_income;
}
