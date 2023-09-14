package com.banking.teamone.controller.auth;

import com.banking.teamone.model.CRole;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.payload.request.LoginRequestIb;
import com.banking.teamone.payload.request.SignUpRequestIb;
import com.banking.teamone.payload.response.JwtResponse;
import com.banking.teamone.security.JwtUtils;
import com.banking.teamone.service.CustomerIbService;
import com.banking.teamone.service.CustomerIbDetailsImpl;
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
    CustomerIbService customerIbService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signinIb")
    @CrossOrigin
    public ResponseEntity<?>authenicateUser(@RequestBody LoginRequestIb loginRequest){
        System.out.println(loginRequest.getUsername()+" "+loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
//System.out.println(jwt);
        CustomerIbDetailsImpl customerIbDetails= (CustomerIbDetailsImpl) authentication.getPrincipal();
        List<String> roles=customerIbDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,customerIbDetails.getUsername(),roles.get(0)));
    }

    @PostMapping("/signup")
    @CrossOrigin
    public ResponseEntity<String>registeredUser(@RequestBody SignUpRequestIb signUpRequestIb){
//        System.out.println(signUpRequestIb.getAccountNo()+" "+signUpRequestIb.getUsername()+" "+signUpRequestIb.getPassword()+" "+signUpRequestIb.getRole());
//             System.out.println(signUpRequestIb.getAccountNo()+" "+signUpRequestIb.getUsername()+" "+signUpRequestIb.getPassword()+" "+signUpRequestIb.getRole());
       String username=signUpRequestIb.getUsername();
       String password=signUpRequestIb.getPassword();
       String accountNo=signUpRequestIb.getAccountNo();
        if(customerIbService.getCustomerByUsername(username)!=null){
            return new ResponseEntity<>("Username is already taken", HttpStatus.OK);
        }
        if(customerIbService.getCustomerByAccountNo(accountNo)!=null){
            return new ResponseEntity<>("Account already registered",HttpStatus.OK);
        }
        CustomerIb customerIb= new CustomerIb();
        customerIb.setUsername(username);
        customerIb.setAccountNo(accountNo);
        customerIb.setPassword(passwordEncoder.encode(password));
        customerIb.setRole(CRole.ROLE_CUSTOMER);
        customerIbService.createCustomerIb(customerIb);
        return new ResponseEntity<>("User Created successfully",HttpStatus.OK);

    }

}
