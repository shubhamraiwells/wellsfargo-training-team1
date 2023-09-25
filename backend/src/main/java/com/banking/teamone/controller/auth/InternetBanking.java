package com.banking.teamone.controller.auth;

import com.banking.teamone.model.CRole;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.payload.request.LoginRequestIb;
import com.banking.teamone.payload.request.SignUpRequestIb;
import com.banking.teamone.payload.response.JwtResponse;
import com.banking.teamone.security.JwtUtils;
import com.banking.teamone.service.AccountService;
import com.banking.teamone.service.CustomerIbService;
import com.banking.teamone.service.CustomerIbDetailsImpl;
import com.banking.teamone.service.SavingsAccountService;
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

    @PostMapping("/signinIb")
    @CrossOrigin
    public ResponseEntity<?>authenicateUser(@RequestBody LoginRequestIb loginRequest){
        try {
            CustomerIb user = customerIbService.getCustomerByUsername(loginRequest.getUsername());
             if (user != null) {
                  if((passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))&& (user.getLockTime()!=null)){
                        if (customerIbService.unlockWhenTimeExpired(user)) {
                            System.out.println("Your account has been unlocked. Please try to login again.");
                        }

                  }
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
                System.out.println(e.getMessage());
                CustomerIb user = customerIbService.getCustomerByUsername(loginRequest.getUsername());
//                System.out.println(user.toString());
                if (user != null) {
                   System.out.println(user.getIsActive()+" "+user.isAccountNonLocked());
                    if (user.getIsActive() && user.isAccountNonLocked()) {
//                        System.out.println(user.getFailedAttempt());
                        if (user.getFailedAttempt() < CustomerIbService.MAX_FAILED_ATTEMPTS - 1) {
                            customerIbService.increaseFailedAttempts(user);
                        } else {
                            customerIbService.lock(user);
                            System.out.println("Your account has been locked due to 3 failed attempts."
                                    + " It will be unlocked after 24 hours.");
                        }
                    }
                }
            return ResponseEntity.ok(null);

        }

    }

    @PostMapping("/signup")
    @CrossOrigin
    public ResponseEntity<String>registeredUser(@RequestBody SignUpRequestIb signUpRequestIb){
       String username=signUpRequestIb.getUsername();
       String password=signUpRequestIb.getPassword();
       String accountNo=signUpRequestIb.getAccountNo();
        if(customerIbService.getCustomerByUsername(username)!=null){
            return new ResponseEntity<>("Username is already taken", HttpStatus.OK);
        }
        if(customerIbService.getCustomerByAccountNo(accountNo)!=null){
            return new ResponseEntity<>("Account already registered",HttpStatus.OK);
        }
        if(accountService.getAccountById(accountNo)==null){
            return new ResponseEntity<>("Account number does not exist",HttpStatus.OK);
        }
        CustomerIb customerIb= new CustomerIb();
        customerIb.setUsername(username);
        customerIb.setAccountNo(accountNo);
        customerIb.setPassword(passwordEncoder.encode(password));
        customerIb.setRole(CRole.ROLE_USER);
        customerIb.setIsActive(true);
        customerIb.setAccountNonLocked(true);
        customerIbService.createCustomerIb(customerIb);
        return new ResponseEntity<>("User Created successfully",HttpStatus.OK);

    }

}
