package com.banking.teamone.controller.admin;


import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.model.Account;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.security.AuthTokenFilter;
import com.banking.teamone.service.AccountService;
import com.banking.teamone.service.AdminService;
import com.banking.teamone.service.SavingsAccountService;
import com.banking.teamone.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
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

    @Autowired
    private SavingsAccountService savingsAccountService;

    private static final Logger logger= LoggerFactory.getLogger(AdminFunctionality.class);


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
            logger.info("Exception occured in get all accounts: "+e.getMessage());
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
    @GetMapping(value="/getAllCustomers")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getAllCustomers(Integer ownerId){
        try{
            CustomerInfo cus =savingsAccountService.getAllCustomers(ownerId);
//            System.out.println(cus);
            return new ResponseEntity<>(savingsAccountService.getAllCustomers(ownerId),HttpStatus.OK);

        }catch (Exception e){
            logger.info("Exception occured in get all customers: "+e.getMessage());
            return new ResponseEntity<>("Some Exception occured in fetching customers",HttpStatus.OK);
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
            logger.info("Exception occured in deactivating user: "+e.getMessage());
            return new ResponseEntity<>("some exception occured in deactivating user",HttpStatus.OK);
        }
    }









}