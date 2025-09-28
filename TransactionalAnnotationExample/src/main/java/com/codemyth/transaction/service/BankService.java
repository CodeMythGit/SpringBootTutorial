package com.codemyth.transaction.service;

import com.codemyth.transaction.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {

    private final AccountService accountService;
    private final TransactionLogService logService;

    public void transferMoney(Long fromAcc, Long toAcc, double amount) {
        try {
            accountService.debit(fromAcc, amount);
            accountService.credit(toAcc, amount);
            logService.logTransaction(fromAcc, toAcc, amount, "SUCCESS", "");
        } catch (Exception e) {
            logService.logTransaction(fromAcc, toAcc, amount, "FAILED", e.getMessage());
            throw e;
        }
    }

    public Account saveAccount(Account account) {
        return accountService.saveAccount(account);
    }

    public List<Account> findAllAccountDetails() {
        return accountService.findAllAccountDetails();
    }
}
