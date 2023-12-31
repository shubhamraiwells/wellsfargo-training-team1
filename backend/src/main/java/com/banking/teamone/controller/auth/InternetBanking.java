package com.banking.teamone.controller.auth;

import com.banking.teamone.model.Account;
import com.banking.teamone.model.CRole;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.model.EmailDetails;
import com.banking.teamone.payload.request.LoginRequestIb;
import com.banking.teamone.payload.request.SignUpRequestIb;
import com.banking.teamone.payload.response.JwtResponse;
import com.banking.teamone.repository.CustomerIbRepository;
import com.banking.teamone.security.AuthTokenFilter;
import com.banking.teamone.security.JwtUtils;
import com.banking.teamone.service.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class InternetBanking {
    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    AccountService accountService;

    @Autowired
    CustomerIbService customerIbService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    CustomerInfoService customerInfoService;


    @Autowired
    EmailService emailService;


    private static final Logger logger= LoggerFactory.getLogger(InternetBanking.class);


    @PostMapping("/signinIb")
    @CrossOrigin
    public ResponseEntity<?>authenicateUser(@RequestBody LoginRequestIb loginRequest){
        try {
            CustomerIb user = customerIbService.getCustomerByUsername(loginRequest.getUsername());            System.out.println(user.getAccountNo());
             if (user != null) {
                  if((passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))&& (user.getLockTime()!=null)){
                        if (customerIbService.unlockWhenTimeExpired(user)) {
                            logger.info("unlocking the user account");
//                            System.out.println("Your account has been unlocked. Please try to login again.");
                            return new ResponseEntity<>("Your account has been unlocked. Please try to login again.",HttpStatus.UNAUTHORIZED);
                        }

                  }
            }else{
                 logger.info("User not found in authenticate user");
                 return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
             }

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword()
            ));
            CustomerIbDetailsImpl customerIbDetails= (CustomerIbDetailsImpl) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            List<String> roles=customerIbDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(jwt,customerIbDetails.getUsername(),roles.get(0)));
        }catch (Exception e){
           logger.info("Exception occured in authenticating user: "+e.getMessage());
            CustomerIb user = customerIbService.getCustomerByUsername(loginRequest.getUsername());
                if (user != null) {
                    if (user.getIsActive() && user.isAccountNonLocked()) {
                        if (user.getFailedAttempt() < CustomerIbService.MAX_FAILED_ATTEMPTS - 1) {
                             logger.info("Increasing the number of failed attempts "+e.getMessage());
                            customerIbService.increaseFailedAttempts(user);
                        } else {
                            logger.info("locking the user account");
                            customerIbService.lock(user);

                            return new ResponseEntity<>("Your Account has been locked due to 3 failed attempts it. it will be unlcoked after 24 hrs",HttpStatus.LOCKED);
                        }
                    }
                    if(!user.getIsActive()){
                        return new ResponseEntity<>("Your account is inactive",HttpStatus.LOCKED);


                    }
                    if(!user.isAccountNonLocked()){
                        return new ResponseEntity<>("Your account is locked",HttpStatus.LOCKED);
                    }
                }
            return new ResponseEntity<>("Incorrect credentials",HttpStatus.NOT_FOUND);

        }

    }

    @PostMapping("/signup")
    @CrossOrigin
    public ResponseEntity<String>registeredUser(@RequestBody SignUpRequestIb signUpRequestIb){
        try {
            String username = signUpRequestIb.getUsername();
            String password = signUpRequestIb.getPassword();
            String accountNo = signUpRequestIb.getAccountNo();
            if (customerIbService.getCustomerByUsername(username) != null) {
                return new ResponseEntity<>("Username is already taken", HttpStatus.OK);
            }
            if (customerIbService.getCustomerByAccountNo(accountNo) != null) {
                return new ResponseEntity<>("Account already registered", HttpStatus.OK);
            }
            if (accountService.getAccountById(accountNo) == null) {
                return new ResponseEntity<>("Account number does not exist", HttpStatus.OK);
            }
            CustomerIb customerIb = new CustomerIb();
            customerIb.setUsername(username);
            customerIb.setAccountNo(accountNo);
            customerIb.setPassword(passwordEncoder.encode(password));
            customerIb.setRole(CRole.ROLE_USER);
            customerIb.setIsActive(true);
            customerIb.setAccountNonLocked(true);
            customerIbService.createCustomerIb(customerIb);
            return new ResponseEntity<>("User Created successfully", HttpStatus.OK);
        }catch (Exception e){
            logger.info("Exception occured in registering user: "+e.getMessage());
            return new ResponseEntity<>("Exception in registering internet banking user"+e.getMessage(),HttpStatus.OK);
        }
    }

    @GetMapping("/forgotpassword")
    @CrossOrigin
    public ResponseEntity<?> forgotPassword(@RequestParam String username){
        CustomerIb customer=customerIbService.getCustomerByUsername(username);
        if(customer==null){
            return new ResponseEntity<>("Username does not exist",HttpStatus.OK);
        }
        Account account=accountService.getAccountById(customer.getAccountNo());
        if(account==null){
            return new ResponseEntity<>("Account not exist for this username",HttpStatus.OK);
        }
        String email= customerInfoService.getEmail(account.getOwnerId());
        char[] possibleCharacters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_").toCharArray();
        String randomStr = RandomStringUtils.random(12, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
        customerIbService.createCustomerIb(new CustomerIb(customer.getUsername(),passwordEncoder.encode(randomStr),customer.getRole(),customer.getAccountNo(),
        customer.getIsActive(),customer.isAccountNonLocked(),customer.getFailedAttempt(),customer.getLockTime()));
        String status=emailService.sendEmail(new EmailDetails(email,"hi your new  password: "+randomStr,"Password team1"));
        return new ResponseEntity<>(status,HttpStatus.OK);

    }



}
