package com.banking.teamone.controller.admin;


import com.banking.teamone.dto.*;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.security.AuthTokenFilter;
import com.banking.teamone.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.payload.response.JwtResponse;
import com.banking.teamone.security.JwtUtils;
import com.banking.teamone.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
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

    @Autowired
    private SavingsAccountService savingsAccountService;

    @Autowired
    private AccountRequestService accountRequestService;

    private static final Logger logger= LoggerFactory.getLogger(AdminController.class);

    /**
     * Endpoint for creating a new admin user.
     *
     * @param adminSignUpDto The AdminSignUpDto containing admin user details.
     * @return A ResponseEntity with a message indicating the result of the admin creation.
     */

    @PostMapping("/signUpAdmin")
    ResponseEntity<String>createAdmin(@RequestBody AdminSignUpDto adminSignUpDto){
       try{

            return new ResponseEntity<>(adminService.createAdmin(adminSignUpDto.getUsername(),passwordEncoder.encode(adminSignUpDto.getPassword())),HttpStatus.OK);
       }catch (Exception e){
           logger.info("Exception in sign up admin: "+e.getMessage());
         return new ResponseEntity<>("Some exception occured", HttpStatus.OK);
       }
  }

    /**
     * Endpoint for authenticating and signing in an admin user.
     *
     * @param adminSignUpDto The AdminSignUpDto containing admin user login credentials.
     * @return A ResponseEntity with a JWT token and user details if login is successful.
     */
  @PostMapping("/signinAdmin")
  ResponseEntity<?>getAdmin(@RequestBody AdminSignUpDto adminSignUpDto){
    try{
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
              adminSignUpDto.getUsername(),adminSignUpDto.getPassword()
      ));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtTokenAdmin(authentication);

      AdminDetailImpl customerIbDetails= (AdminDetailImpl) authentication.getPrincipal();
      List<String> roles=customerIbDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
      return ResponseEntity.ok(new JwtResponse(jwt,customerIbDetails.getUsername(),roles.get(0)));
    }catch (Exception e){
        logger.info("Exception in sign in admin: "+e.getMessage());
      return new ResponseEntity<>("some exception occured",HttpStatus.OK);
    }
  }

    /**
     * Endpoint for fetching all users.
     *
     * @return A ResponseEntity with a list of users.
     */
    @PostMapping("/fetchUsers")
    @CrossOrigin
    public ResponseEntity<List<?>>fetchAllUsers(){
        List<?> customerInfo1=savingsAccountService.getAllCustomersBySpecificColumn();
        return new ResponseEntity<>(customerInfo1,HttpStatus.OK);
    }

    /**
     * Endpoint for searching for a user by first name.
     *
     * @param firstName The first name to search for.
     * @return A ResponseEntity with the user information if found, or an error message if not found.
     */
    @PostMapping("/search")
    @CrossOrigin

    public ResponseEntity<?>search(@RequestBody String firstName) {
        CustomerInfo customerInfo1 = savingsAccountService.getCustomerByFirstName(firstName);
        if (customerInfo1 == null) {
            return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(customerInfo1, HttpStatus.OK);
        }
    }

    /**
     * Endpoint for getting all pending account requests.
     *
     * @return A ResponseEntity with a list of pending account requests.
     */
    @GetMapping("/getPendingRequests")
    @CrossOrigin

    ResponseEntity<List<PendingRequestModel>> getPendingRequests() {
        try {
            List<PendingRequestModel> pendingRequestList = accountRequestService.getAllPendingRequests();
            return new ResponseEntity<>(pendingRequestList, HttpStatus.OK);
        }catch (Exception e){
            logger.info("Exception in getpendingrequest: "+e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
    }

    /**
     * Endpoint for approving a bank account request.
     *
     * @param approveBankAccountModel The model containing information about the account request to approve.
     * @return A ResponseEntity with a message indicating the result of the approval.
     */
    @PostMapping("/approveBankAccount")
    @CrossOrigin

    ResponseEntity<String> approveBankAccount(@RequestBody ApproveBankAccountModel approveBankAccountModel) {
        try {
            return new ResponseEntity<>(adminService.approveBankAccount(approveBankAccountModel), HttpStatus.OK);
        }catch(Exception e){
            logger.info("Exception in approveBankAccount: "+e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        }








}
