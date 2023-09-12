package com.banking.teamone.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class JwtResponse {
    private String token;
    private String type= "Bearer";
    private String username;
    private String role;


    public JwtResponse(String jwt, String username, String role) {
        this.token=jwt;
        this.username=username;
        this.role=role;
    }
}
