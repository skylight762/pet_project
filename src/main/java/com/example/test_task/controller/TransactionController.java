package com.example.test_task.controller;

import com.example.test_task.dto.AccRequestDTO;
import com.example.test_task.dto.AccRequestForTransferDTO;
import com.example.test_task.exceptions.CustomValidatorException;
import com.example.test_task.exceptions.TransferException;
import com.example.test_task.service.AccountOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Validated
@Tag(name = "Контроллер транзакций", description = "контроллер отвечающий за денежные операции со счетами")
public class TransactionController {

    @Autowired
    RabbitTemplate template;
    private final AccountOperationService accountOperationService;

    public TransactionController(AccountOperationService accountOperationService) {
        this.accountOperationService = accountOperationService;
    }

    @Operation(
            summary = "Пополнение",
            description = "Внесение денежной суммы (sum) на счет по ид (id) счета"
    )
    @PatchMapping("accounts/refill")
    public void putMoneyById(@RequestBody AccRequestDTO accRequestDTO) throws TransferException {
        accountOperationService.putMoney(accRequestDTO);
    }

    @Operation(
            summary = "Списание",
            description = "Списание денежной суммы (sum) со счета по ид (id) счета"
    )
    @PatchMapping("accounts/withdraw")
    public void removeMoneyById(@RequestBody AccRequestDTO accRequestDTO) throws  TransferException {
        accountOperationService.removeMoney(accRequestDTO);
    }

    @Operation(
            summary = "Перевод",
            description = "Перевод денежной суммы (transferSum) со счета (idFrom) на счет (idTo) по ид счетов"
    )
    @PatchMapping("accounts/transfer")
    public void transferById(@RequestBody AccRequestForTransferDTO accRequestForTransferDTO) throws TransferException {
        accountOperationService.transferMoney(accRequestForTransferDTO);
    }


}
