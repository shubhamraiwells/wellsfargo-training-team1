package com.banking.teamone.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;


@Getter
@Entity
@Setter
@Table(name="customerIb")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerIb {
    @Getter
    @Id
    @Column(nullable = false,name = "username")
    @Pattern(regexp = "^(?!.*\\.\\.)(?!.*\\.$)[A-Za-z0-9_.]{8,20}$")
    private String username;

    @Getter
    @Column(nullable = false,name = "password")
    private String password;




    @Enumerated(EnumType.STRING)
    private CRole role;


    @Column(name="acc_no",nullable = false)
    private String accountNo;

    @Column
    private Boolean isActive;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "failed_attempt")
    private int failedAttempt;

    @Column(name = "lock_time")
    private Date lockTime;



}
