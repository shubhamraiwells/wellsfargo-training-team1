package com.banking.teamone.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequestIb {
    @Pattern(regexp = "^(?!.*\\.\\.)(?!.*\\.$)[A-Za-z0-9_.]{8,20}$")
    private String username;

    private String password;

    @Pattern(regexp = "^\\d{9,18}$")
    private String accountNo;


    private String role="ROLE_CUSTOMER";
}
