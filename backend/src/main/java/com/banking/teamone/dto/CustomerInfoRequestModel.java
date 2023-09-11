package com.banking.teamone.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomerInfoRequestModel {

    private String accountType;

    @NotBlank
    private String aadharCardNo;

    private String title;

    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String fatherName;

    @NotBlank
    private String mobileNo;

    @Email
    private String emailId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    @NotNull
    private Date dateOfBirth;

    @NotBlank
    private String residentialAddressLine1;

    @NotBlank
    private String residentialAddressLine2;

    private String residentialLandmark;

    @NotBlank
    private String residentialCity;

    @NotBlank
    private String residentialPincode;

    private String permanentAddressLine1;

    private String permanentAddressLine2;

    private String permanentLandmark;

    private String permanentCity;

    private String permanentPincode;

    @NotBlank
    private String occupationType;

    @NotBlank
    private String sourceOfIncome;

    @NotNull
    private BigDecimal grossAnnualIncome;

}
