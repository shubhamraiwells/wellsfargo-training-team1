package com.banking.teamone.controller.admin;


import com.banking.teamone.model.Account;
import com.banking.teamone.service.AccountService;
import com.banking.teamone.service.AdminService;
import com.banking.teamone.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminFunctionality {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;



    @GetMapping("/resource")
    public String getResource() {
        // Get the currently authenticated user's authorities (roles)
        String res="";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                // User has the 'ADMIN' role
                res=res+" "+ "Admin Resource";
            }
            if (authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"))) {
                // User has the 'USER' role
                res=res+" "+ "User Resource";
            }
        }else {
            // Handle unauthorized access or other cases
            res = res + "Unauthorized";
        }
        return  res;
    }
    @GetMapping("/getAllAccounts")
    @Secured("ROLE_ADMIN")
    ResponseEntity<?>gettingAllAccount(){
        try {
            return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Some Exception occured",HttpStatus.OK);
        }
    }

    @GetMapping("/getAllTransactions")
    @Secured("ROLE_ADMIN")
    ResponseEntity<?>gettingAllTransactions(){
        try{
            return new ResponseEntity<>(transactionService.getAllTransactions(),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>("Some Exception occured in fetching all transactions",HttpStatus.OK);
        }
    }

    @GetMapping("/deactivateUser")
    @Secured("ROLE_ADMIN")
    ResponseEntity<?>deactivateUser(String accountNo){
        try{
            Account getAccount=accountService.getAccountById(accountNo);
            if(getAccount==null){
                return  new ResponseEntity<>("Account does not exist",HttpStatus.OK);
            }
            accountService.createAccount(new Account(getAccount.getId(),getAccount.getAccountType(),getAccount.getOwnerId(),
                    !getAccount.getIsActive(),getAccount.getAccountActivationDate(),getAccount.getTotalBalance()));
            return new ResponseEntity<>("Account status changes",HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>("some exception occured in deactivating user",HttpStatus.OK);
        }
    }









}
