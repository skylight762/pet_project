package com.example.test_task.validation;

import com.example.test_task.dto.AccRequestDTO;
import com.example.test_task.dto.AccRequestForTransferDTO;
import com.example.test_task.entity.Account;
import com.example.test_task.exceptions.CustomValidatorException;
import com.example.test_task.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomValidator {
    private final AccountRepository accRepo;


    public CustomValidator(AccountRepository accRepo) {
        this.accRepo = accRepo;
    }

    public boolean validateId(Integer id) throws CustomValidatorException {
        if (accRepo.getAccountByAccountId(id) == null) {
            throw new CustomValidatorException("неверный номер счета");
        } else return true;
    }

    public boolean validateSum(BigDecimal sum) throws CustomValidatorException {
        if (sum.signum() < 0) {
            throw new CustomValidatorException("Некорректная сумма");
        } else return true;
    }

    public boolean validateTransferSum(Integer id, BigDecimal transferSum) throws CustomValidatorException {
        Account acc = accRepo.getAccountByAccountId(id);
        if (acc.getSum().compareTo(transferSum) < 0) {
            throw new CustomValidatorException("недостаточно средств для перевода");
        } else return true;
    }

    public boolean validateWithdrawSum(Integer id, BigDecimal withdrawSum) throws CustomValidatorException {
        Account acc = accRepo.getAccountByAccountId(id);
        if (acc.getSum().compareTo(withdrawSum) < 0) {
            throw new CustomValidatorException("недостаточно средств для списания");
        } else return true;
    }

    public boolean validateName(String name) {
        return name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");
    }
}
