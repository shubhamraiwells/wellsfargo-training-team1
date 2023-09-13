package com.banking.teamone.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestModel {
	private String username;
  
    private String password;
}
