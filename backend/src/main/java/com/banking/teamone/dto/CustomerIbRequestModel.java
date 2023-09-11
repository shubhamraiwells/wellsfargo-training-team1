package com.banking.teamone.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class CustomerIbRequestModel {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String accountNo;

}
