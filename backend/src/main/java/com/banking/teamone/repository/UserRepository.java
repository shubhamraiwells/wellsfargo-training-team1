package com.banking.teamone.repository;
import org.springframework.data.repository.CrudRepository;

import com.banking.teamone.model.UserModel;

public interface UserRepository extends CrudRepository <UserModel, Integer> {
	UserModel findByName(String name);

}
