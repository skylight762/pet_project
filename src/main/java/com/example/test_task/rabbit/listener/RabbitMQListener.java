package com.example.test_task.rabbit.listener;

import com.example.test_task.dto.AccRequestDTO;
import com.example.test_task.dto.AccRequestForTransferDTO;
import com.example.test_task.service.AccountOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQListener {
    private final AccountOperationService accountOperationService;

    public RabbitMQListener(AccountOperationService accountOperationService) {
        this.accountOperationService = accountOperationService;
    }

    @RabbitListener(queues = "RefillQueue")
    public void refillListener(AccRequestDTO accRequestDTO) {
        log.info("Получено сообщение на refillListener {}", accRequestDTO);
        accountOperationService.putMoney(accRequestDTO);
    }
    @RabbitListener(queues = "WithdrawQueue")
    public void withdrawListener(AccRequestDTO accRequestDTO){
        log.info("Получено сообщение на withdrawListener {}", accRequestDTO);
        accountOperationService.removeMoney(accRequestDTO);
    }
    @RabbitListener(queues = "TransferQueue")
    public void transferListener(AccRequestForTransferDTO accRequestForTransferDTO){
        log.info("Получено сообщение на transferListener {}", accRequestForTransferDTO);
        accountOperationService.transferMoney(accRequestForTransferDTO);
    }
}
