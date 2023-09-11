//package com.banking.teamone.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.banking.teamone.model.UserModel;
//import com.banking.teamone.repository.UserRepository;
//
//@RestController
//@RequestMapping("/auth")
//public class UserController {
//	@Autowired
//	private UserRepository userRepository;
//	@GetMapping(value="/users")
//    public Iterable<UserModel> setUser() {
//        UserModel userModel = new UserModel("Sanjay",38);
//        userModel.getName();
//        return this.userRepository.findAll();
////        this.userRepository.save(userModel);
////        return userModel;
//	}
//}
