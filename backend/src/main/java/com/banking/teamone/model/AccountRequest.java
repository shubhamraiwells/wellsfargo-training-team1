package com.banking.teamone.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "account_request")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {

    @Id
    private String id;

    @Column(name = "acc_type", length = 50, nullable = false,columnDefinition = "varchar(50) default 'saving'")
    private String accountType;

    @Column
    private Integer ownerId;
}
