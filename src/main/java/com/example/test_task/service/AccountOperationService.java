package com.example.test_task.service;

import com.example.test_task.dto.AccRequestDTO;
import com.example.test_task.dto.AccRequestForTransferDTO;
import com.example.test_task.entity.Account;
import com.example.test_task.exceptions.TransferException;
import com.example.test_task.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
@Slf4j
public class AccountOperationService {
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public AccountOperationService(AccountRepository repository, TransactionService transactionService) {
        this.accountRepository = repository;
        this.transactionService = transactionService;
    }


    @Transactional
    public void putMoney(AccRequestDTO accRequestDTO) {
        Account acc = accountRepository.getAccountByAccountId(accRequestDTO.getId());
        log.info("Пополнение счета № {} на сумму {} завершено", accRequestDTO.getId(), accRequestDTO.getSum());
        acc.setSum(acc.getSum().add(accRequestDTO.getSum()));
        accountRepository.save(acc);
        transactionService.createTransaction(
                accRequestDTO.getId(),
                accRequestDTO.getSum(),
                "Пополнение счета");
    }

    @Transactional
    public void removeMoney(AccRequestDTO accRequestDTO) throws TransferException {
        Account acc = accountRepository.getAccountByAccountId(accRequestDTO.getId());
        log.info("Списание со счета № {} суммы {}", acc.getAccountId(), accRequestDTO.getSum());
        if (acc.getSum().compareTo(accRequestDTO.getSum()) >= 0) {
            acc.setSum(acc.getSum().subtract(accRequestDTO.getSum()));
            accountRepository.save(acc);
            transactionService.createTransaction(
                    accRequestDTO.getId(),
                    accRequestDTO.getSum(),
                    "Списание со счета");


        } else throw new TransferException("недостаточно средств для списания");
    }

    @Transactional
    public void transferMoney(AccRequestForTransferDTO accRequestForTransferDTO) throws TransferException {
        Account acc1 = accountRepository.getAccountByAccountId(accRequestForTransferDTO.getIdFrom());
        Account acc2 = accountRepository.getAccountByAccountId(accRequestForTransferDTO.getIdTo());
        log.info("Перевод со счета № {} на счет № {} сумма {}", acc1.getAccountId(), acc2.getAccountId(), accRequestForTransferDTO.getTransferSum());
        if (acc1.getSum().compareTo(accRequestForTransferDTO.getTransferSum()) >= 0) {
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

        } else throw new TransferException("недостаточно средств для перевода");
    }
}
