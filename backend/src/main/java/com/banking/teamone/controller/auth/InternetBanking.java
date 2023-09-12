package com.banking.teamone.controller.auth;

import com.banking.teamone.model.CRole;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.payload.request.LoginRequestIb;
import com.banking.teamone.payload.request.SignUpRequestIb;
import com.banking.teamone.payload.response.JwtResponse;
import com.banking.teamone.repository.CustomerIbRepository;
import com.banking.teamone.security.jwt.JwtUtils;
import com.banking.teamone.service.CustomerIbService;
import com.banking.teamone.service.auth.CustomerIbDetailsImpl;
import org.apache.coyote.Response;
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
    public ResponseEntity<?>authenicateUser(@Valid @RequestBody LoginRequestIb loginRequest){
//        System.out.println(loginRequest.getUsername()+" "+loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
System.out.println(jwt);
        CustomerIbDetailsImpl customerIbDetails= (CustomerIbDetailsImpl) authentication.getPrincipal();
        List<String> roles=customerIbDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,customerIbDetails.getUsername(),roles.get(0)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?>registeredUser(@Valid @RequestBody SignUpRequestIb signUpRequestIb){
        if(customerIbService.getCustomerByUsername(signUpRequestIb.getUsername())!=null){
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
        CustomerIb customerIb= new CustomerIb();
        customerIb.setUsername(signUpRequestIb.getUsername());
        customerIb.setAccount_no(signUpRequestIb.getAccountNo());
        customerIb.setPassword(passwordEncoder.encode(signUpRequestIb.getPassword()));
        customerIb.setRole(CRole.ROLE_CUSTOMER);
        customerIbService.createCustomerIb(customerIb);
        return new ResponseEntity<>("User Created successfully",HttpStatus.OK);

    }

}
