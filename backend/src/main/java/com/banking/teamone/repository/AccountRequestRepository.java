package com.banking.teamone.repository;

import com.banking.teamone.model.AccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRequestRepository extends JpaRepository<AccountRequest,String> {
}
