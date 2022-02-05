package com.example.test_task.rabbit.listener;

import com.example.test_task.dto.AccRequestDTO;
import com.example.test_task.dto.AccRequestForTransferDTO;
import com.example.test_task.exceptions.CustomValidatorException;
import com.example.test_task.exceptions.TransferException;
import com.example.test_task.service.AccountOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQListener {

    private RabbitTemplate template;
    private final AccountOperationService accountOperationService;

    public RabbitMQListener(RabbitTemplate template, AccountOperationService accountOperationService) {
        this.template = template;
        this.accountOperationService = accountOperationService;
    }

    @RabbitListener(queues = "RefillQueue")
    public void refillListener(AccRequestDTO accRequestDTO)  {
        log.info("Получено сообщение на refillListener {}", accRequestDTO);
        try {
            accountOperationService.putMoney(accRequestDTO);
        } catch (TransferException te) {
            template.convertAndSend("transfer_error",  accRequestDTO);
        }
    }

    @RabbitListener(queues = "WithdrawQueue")
    public void withdrawListener(AccRequestDTO accRequestDTO)  {
        log.info("Получено сообщение на withdrawListener {}", accRequestDTO);
        try {
            accountOperationService.removeMoney(accRequestDTO);
        } catch (TransferException te) {
            template.convertAndSend("transfer_error",  accRequestDTO);
        }
    }

    @RabbitListener(queues = "TransferQueue")
    public void transferListener(AccRequestForTransferDTO accRequestForTransferDTO)  {
        log.info("Получено сообщение на transferListener {}", accRequestForTransferDTO);
        try {
            accountOperationService.transferMoney(accRequestForTransferDTO);
        } catch (TransferException te) {
            template.convertAndSend("transfer_error",  accRequestForTransferDTO);
        }
    }
    @RabbitListener(queues = "ExceptionQueue")
    public void exceptionListenerOne(AccRequestDTO accRequestDTO){
        log.info("Получено сообщение на exceptionListenerOne message {} ",accRequestDTO);
    }
    @RabbitListener(queues = "ExceptionQueue")
    public void exceptionListenerTwo(AccRequestForTransferDTO accRequestForTransferDTO){
        log.info("Получено сообщение на exceptionListenerTWO message {} ",accRequestForTransferDTO);
    }
}
