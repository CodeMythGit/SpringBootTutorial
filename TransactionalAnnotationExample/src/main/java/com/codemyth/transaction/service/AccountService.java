package com.codemyth.transaction.service;

import com.codemyth.transaction.entity.Account;
import com.codemyth.transaction.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    public void debit(Long accountId, double amount) {
        Account acc = accountRepository.findById(accountId).orElseThrow();
        acc.setBalance(acc.getBalance() - amount);
        accountRepository.save(acc);

        if(amount > 1000) {
            throw new RuntimeException("Debit too large → rollback only debit");
        }
    }

    public void credit(Long accountId, double amount) {
        Account acc = accountRepository.findById(accountId).orElseThrow();
        acc.setBalance(acc.getBalance() + amount);
        accountRepository.save(acc);

        if(amount > 500) {
            throw new RuntimeException("Credit too large → rollback both");
        }
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> findAllAccountDetails() {
        return accountRepository.findAll();
    }
}
