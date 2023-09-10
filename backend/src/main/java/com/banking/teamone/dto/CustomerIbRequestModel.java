package com.banking.teamone.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerIbRequestModel {

    @NotNull(message = "username cannot be blank")
    @Size(min = 1, message = "Username can't be blank")
    private String username;

    @Size(min = 8, max = 16, message = "Password must be between 8-16 characters")
    private String password;

    private Integer accountNo;

}
