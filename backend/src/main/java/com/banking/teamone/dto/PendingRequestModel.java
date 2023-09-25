package com.banking.teamone.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PendingRequestModel {

    @NotNull
    private String accountNo;

    private String accountType;

    @NotNull
    private String aadharCardNo;

    private String title;

    @NotNull
    private String firstName;

    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    private String fatherName;

    @NotNull
    private String mobileNo;

    @Email
    private String emailId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    @NotNull
    private Date dateOfBirth;

    @NotNull
    private String residentialAddressLine1;

    @NotNull
    private String residentialAddressLine2;

    private String residentialLandmark;

    @NotNull
    private String residentialCity;

    @NotNull
    private String residentialPincode;

    private String permanentAddressLine1;

    private String permanentAddressLine2;

    private String permanentLandmark;

    private String permanentCity;

    private String permanentPincode;

    @NotNull
    private String occupationType;

    @NotNull
    private String sourceOfIncome;

    @NotNull
    private BigDecimal grossAnnualIncome;

    private Integer Id;
}
