package com.banking.teamone.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin {
    @Id
    @Column(nullable = false)
    private String username;


    @Column(nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)
    private CRole role;
}
