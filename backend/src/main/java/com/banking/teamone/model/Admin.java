package com.banking.teamone.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(nullable = false)
    private String emailId;


    @Column(nullable = false)
    private String password;

}
