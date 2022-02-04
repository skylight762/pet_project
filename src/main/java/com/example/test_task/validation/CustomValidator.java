package com.example.test_task.validation;

import com.example.test_task.dto.AccRequestDTO;
import com.example.test_task.dto.AccRequestForTransferDTO;
import com.example.test_task.entity.Account;
import com.example.test_task.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomValidator {
    private final AccountRepository accRepo;


    public CustomValidator(AccountRepository accRepo) {
        this.accRepo = accRepo;
    }

    public boolean validateId(Integer id) {
        return accRepo.getAccountByAccountId(id) != null;
    }

    public boolean validateSum(BigDecimal sum) {
        return sum.signum() > 0;
    }

    public boolean validateTransferSum(Integer id, BigDecimal transferSum) {
        Account acc = accRepo.getAccountByAccountId(id);
        return acc.getSum().compareTo(transferSum) >= 0;
    }

    public boolean validateWithdrawSum(Integer id, BigDecimal withdrawSum) {
        Account acc = accRepo.getAccountByAccountId(id);
        return acc.getSum().compareTo(withdrawSum) >= 0;
    }
    public  boolean validateName(String name){
        return name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");
    }
}
