package com.banking.teamone.controller.admin;


import com.banking.teamone.dto.AdminSignUpDto;
import com.banking.teamone.payload.response.JwtResponse;
import com.banking.teamone.security.JwtUtils;
import com.banking.teamone.service.AdminDetailImpl;
import com.banking.teamone.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/auth")
public class AdminController {
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  private AdminService adminService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signUpAdmin")
    ResponseEntity<String>createAdmin(@RequestBody AdminSignUpDto adminSignUpDto){
       try{

            return new ResponseEntity<>(adminService.createAdmin(adminSignUpDto.getUsername(),passwordEncoder.encode(adminSignUpDto.getPassword())),HttpStatus.OK);
       }catch (Exception e){
         return new ResponseEntity<>("Some exception occured", HttpStatus.OK);
       }
  }

  @PostMapping("/signinAdmin")
  ResponseEntity<?>getAdmin(@RequestBody AdminSignUpDto adminSignUpDto){
    try{

      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
              adminSignUpDto.getUsername(),adminSignUpDto.getPassword()
      ));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtTokenAdmin(authentication);
//System.out.println(jwt);
      AdminDetailImpl customerIbDetails= (AdminDetailImpl) authentication.getPrincipal();
      List<String> roles=customerIbDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
      return ResponseEntity.ok(new JwtResponse(jwt,customerIbDetails.getUsername(),roles.get(0)));

    }catch (Exception e){
      System.out.println(e.getMessage());
      return new ResponseEntity<>("some exception occured",HttpStatus.OK);
    }
  }








}
