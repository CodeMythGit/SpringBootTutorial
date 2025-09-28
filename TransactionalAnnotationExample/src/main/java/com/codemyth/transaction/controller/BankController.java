package com.codemyth.transaction.controller;

import com.codemyth.transaction.entity.Account;
import com.codemyth.transaction.entity.TransactionLog;
import com.codemyth.transaction.service.BankService;
import com.codemyth.transaction.service.TransactionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;
    private final TransactionLogService transactionLogService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestParam("fromAccId") Long fromAccId,
                                                @RequestParam("toAccId") Long toAccId,
                                                @RequestParam("amount") Double amount){
        bankService.transferMoney(fromAccId, toAccId, amount);
        return ResponseEntity.accepted().body("Success");
    }

    @PostMapping("/save")
    public ResponseEntity<Account> saveAccount(@RequestBody Account account){
        return ResponseEntity.status(HttpStatus.CREATED).body(bankService.saveAccount(account));
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccountDetails(){
        return ResponseEntity.status(HttpStatus.FOUND).body(bankService.findAllAccountDetails());
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<TransactionLog>> transactionDetails(){
        return ResponseEntity.status(HttpStatus.FOUND).body(transactionLogService.transactionDetails());
    }
}
