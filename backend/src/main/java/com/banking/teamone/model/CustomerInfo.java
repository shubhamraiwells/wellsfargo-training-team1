package com.banking.teamone.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "customer_info")
public class CustomerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="account_type")
    private String accountType;


    @Column(name = "aadhar_card_no", length = 12, unique = true)
    private String aadharCardNo;

    @Column(name = "title", length = 10)
    private String title;

    @Column(name = "first_name", length = 255, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 255)
    private String middleName;

    @Column(name = "last_name", length = 255, nullable = false)
    private String lastName;

    @Column(name = "father_name", length = 255, nullable = false)
    private String fatherName;

    @Column(name = "mobile_no", length = 15, nullable = false)
    private String mobileNo;

    @Column(name = "email_id", length = 255)
    private String emailId;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "residential_address_line_1", length = 255, nullable = false)
    private String residentialAddressLine1;

    @Column(name = "residential_address_line_2", length = 255, nullable = false)
    private String residentialAddressLine2;

    @Column(name = "residential_landmark", length = 255)
    private String residentialLandmark;

    @Column(name = "residential_city", length = 255, nullable = false)
    private String residentialCity;

    @Column(name = "residential_pincode", length = 10, nullable = false)
    private String residentialPincode;

    @Column(name = "permanent_address_line_1", length = 255)
    private String permanentAddressLine1;

    @Column(name = "permanent_address_line_2", length = 255)
    private String permanentAddressLine2;

    @Column(name = "permanent_landmark", length = 255)
    private String permanentLandmark;

    @Column(name = "permanent_city", length = 255)
    private String permanentCity;

    @Column(name = "permanent_pincode", length = 10)
    private String permanentPincode;

    @Column(name = "occupation_type", length = 50, nullable = false)
    private String occupationType;

    @Column(name = "source_of_income", length = 50, nullable = false)
    private String sourceOfIncome;

    @Column(name = "gross_annual_income", precision = 10, scale = 2, nullable = false)
    private BigDecimal grossAnnualIncome;

    // Constructors, getters, and setters go here

}

