package com.example.test_task.service;

import com.example.test_task.dto.AccRequestDTO;
import com.example.test_task.dto.AccRequestForTransferDTO;
import com.example.test_task.entity.Account;
import com.example.test_task.exceptions.TransferException;
import com.example.test_task.repository.AccountRepository;
import com.example.test_task.validation.CustomValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;


@Service
@Slf4j
@Validated
public class AccountOperationService {
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    private final CustomValidator customValidator;

    public AccountOperationService(AccountRepository repository,
                                   TransactionService transactionService,
                                   CustomValidator customValidator) {
        this.accountRepository = repository;
        this.transactionService = transactionService;
        this.customValidator = customValidator;
    }


    @Transactional
    public void putMoney(AccRequestDTO accRequestDTO) {
        if (customValidator.validateId(accRequestDTO.getId())) {
            if (customValidator.validateSum(accRequestDTO.getSum())) {
                Account acc = accountRepository.getAccountByAccountId(accRequestDTO.getId());
                acc.setSum(acc.getSum().add(accRequestDTO.getSum()));
                accountRepository.save(acc);
                transactionService.createTransaction(
                        accRequestDTO.getId(),
                        accRequestDTO.getSum(),
                        "Пополнение счета");
                log.info("Пополнение счета № {} на сумму {} завершено", accRequestDTO.getId(), accRequestDTO.getSum());
            } else log.info("введена некорректная сумма");
        } else log.info("неверный номер счета");
    }

    @Transactional
    public void removeMoney(AccRequestDTO accRequestDTO) throws TransferException {
        if (customValidator.validateId(accRequestDTO.getId())) {
            if (customValidator.validateSum(accRequestDTO.getSum())) {
                if (customValidator.validateWithdrawSum(accRequestDTO.getId(), accRequestDTO.getSum())) {
                    Account acc = accountRepository.getAccountByAccountId(accRequestDTO.getId());
                    acc.setSum(acc.getSum().subtract(accRequestDTO.getSum()));
                    accountRepository.save(acc);
                    transactionService.createTransaction(
                            accRequestDTO.getId(),
                            accRequestDTO.getSum(),
                            "Списание со счета");
                    log.info("Списание со счета № {} суммы {}", acc.getAccountId(), accRequestDTO.getSum());
                } else log.info("Недостаточно средств для совершения операции");
            } else log.info("введена некорректная сумма");
        } else log.info("неверный номер счета");
    }

    @Transactional
    public void transferMoney(AccRequestForTransferDTO accRequestForTransferDTO) throws TransferException {
        if (customValidator.validateId(accRequestForTransferDTO.getIdFrom())) {
            if (customValidator.validateId(accRequestForTransferDTO.getIdTo())) {
                if (customValidator.validateTransferSum(accRequestForTransferDTO.getIdFrom(), accRequestForTransferDTO.getTransferSum())) {
                    Account acc1 = accountRepository.getAccountByAccountId(accRequestForTransferDTO.getIdFrom());
                    Account acc2 = accountRepository.getAccountByAccountId(accRequestForTransferDTO.getIdTo());
                    acc1.setSum(acc1.getSum().subtract(accRequestForTransferDTO.getTransferSum()));
                    acc2.setSum(acc2.getSum().add(accRequestForTransferDTO.getTransferSum()));
                    accountRepository.save(acc1);
                    accountRepository.save(acc2);
                    transactionService.createTransaction(
                            accRequestForTransferDTO.getIdFrom(),
                            accRequestForTransferDTO.getTransferSum(),
                            "Перевод на счет " + accRequestForTransferDTO.getIdTo());
                    transactionService.createTransaction(
                            accRequestForTransferDTO.getIdTo(),
                            accRequestForTransferDTO.getTransferSum(),
                            "перевод со счета " + accRequestForTransferDTO.getIdFrom());
                    log.info("Перевод со счета № {} на счет № {} сумма {}", acc1.getAccountId(), acc2.getAccountId(), accRequestForTransferDTO.getTransferSum());
                } else log.info("Недостаточно средств для совершения операции");
            } else log.info("Неверный номер счета пополнения");
        } else log.info("Неверный номер счета для списания средств");
    }
}
