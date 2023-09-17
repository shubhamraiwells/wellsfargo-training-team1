package com.banking.teamone.controller.transactions;


import com.banking.teamone.dto.TransactionDto;
import com.banking.teamone.dto.TransactionRequestDto;
import com.banking.teamone.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    @Autowired
    private  TransactionService transactionService;

    @GetMapping("/getTransactions")
    public ResponseEntity<List<TransactionDto>>getAllTransactionsByAccount(@RequestParam String accountNo){
        List<TransactionDto>res=transactionService.getAllTransactionByAccountNo(accountNo);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/getTransactionsByDate")
    public ResponseEntity<List<TransactionDto>>getAllTransactionsByAccount(@RequestParam("accountNo") String accountNo, @RequestParam(value="fromDate") @DateTimeFormat(pattern="dd-MM-yyyy") Date fromDate,@RequestParam(value="fromDate")  @DateTimeFormat(pattern="dd-MM-yyyy") Date toDate){
        List<TransactionDto>res=transactionService.getAllTransactionByAccountNoAndDate(accountNo,fromDate,toDate);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/createTransaction")
    public ResponseEntity<String>createTransaction(@RequestBody TransactionRequestDto transaction){
        try{
            System.out.println(transaction.getTransactionAmount()+transaction.getFromAccountNo()+" "+transaction.getToAccountNo());
            transactionService.createTransaction(transaction);
            return new ResponseEntity<>("Transaction Registered",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Error performing transaction",HttpStatus.BAD_REQUEST);
        }
    }




}
