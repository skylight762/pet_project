package com.example.test_task.controller;

import com.example.test_task.dto.AccRequestDTO;
import com.example.test_task.dto.AccRequestForTransferDTO;
import com.example.test_task.exceptions.TransferException;
import com.example.test_task.service.AccountOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Tag(name = "Контроллер транзакций", description = "контроллер отвечающий за денежные операции со счетами")
public class TransactionController {
    @Autowired
    private final AccountOperationService accountOperationService;

    public TransactionController(AccountOperationService accountOperationService) {
        this.accountOperationService = accountOperationService;
    }
    @Operation(
            summary = "Пополнение",
            description = "Пополнение счета по номеру счета"
    )
    @PatchMapping("accounts/refill")
    public void putMoneyById(@RequestBody AccRequestDTO accRequestDTO){
        accountOperationService.putMoney(accRequestDTO);
    }
    @PatchMapping("accounts/withdraw")
    public void removeMoneyById(@RequestBody AccRequestDTO accRequestDTO) throws TransferException {
        accountOperationService.removeMoney(accRequestDTO);
    }
    @PatchMapping("accounts/transfer")
    public void transferById(@RequestBody AccRequestForTransferDTO accRequestForTransferDTO) throws TransferException {
        accountOperationService.transferMoney(accRequestForTransferDTO);
    }
}
