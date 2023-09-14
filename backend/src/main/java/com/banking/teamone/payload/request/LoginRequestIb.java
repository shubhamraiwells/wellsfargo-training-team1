package com.banking.teamone.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestIb {
    @NotBlank
    @Pattern(regexp = "^(?!.*\\.\\.)(?!.*\\.$)[A-Za-z0-9_.]{8,20}$")
    private String username;

    @NotBlank
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$")
    private String password;




}
