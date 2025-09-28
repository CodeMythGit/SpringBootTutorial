package com.codemyth.transaction.service;


import com.codemyth.transaction.entity.TransactionLog;
import com.codemyth.transaction.repository.TransactionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionLogService {

    private final TransactionLogRepository logRepository;

    public void logTransaction(Long fromAccount, Long toAccount, double amount, String status, String message) {
        TransactionLog log = new TransactionLog(null, fromAccount, toAccount, amount, status, message);
        logRepository.save(log);
    }

    public List<TransactionLog> transactionDetails() {
        return logRepository.findAll();
    }
}
