package com.banking.teamone.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;


@Getter
@Entity
@Table(name="customerIb")
public class CustomerIb {
    @Id
    @Column(nullable = false,name = "username")
    @Pattern(regexp = "^(?!.*\\.\\.)(?!.*\\.$)[A-Za-z0-9_.]{8,20}$")
    private String username;

    @Column(nullable = false,name = "password")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$")
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNo(String account_no) {
        this.accountNo = account_no;
    }

   @Pattern(regexp = "^\\d{9,18}$")
    @Column(name="acc_no",nullable = false)
    private String accountNo;
}
