package com.example.test_task.service;

import com.example.test_task.entity.Transaction;
import com.example.test_task.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Service
public class TransactionService {
    TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Integer id, BigDecimal sum, String type) {
        final Transaction transaction;
        transaction = new Transaction().
                setAccountId(id).
                setValue(sum).
                setOperationName(type).
                setEndTime(ZonedDateTime.now());
        return transactionRepository.save(transaction);
    }
}
