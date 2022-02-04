package com.example.test_task.controller;

import com.example.test_task.dto.AccRequestDTO;
import com.example.test_task.dto.AccRequestForTransferDTO;
import com.example.test_task.service.AccountOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@Tag (name = "RabbitMQ контроллер", description = "Контроллер содержит вариант проведения операций со счетами с помощью RabbitMQ")
public class SomeRabbitController {

    final RabbitTemplate template;


    public SomeRabbitController(RabbitTemplate template) {
        this.template = template;
    }


     @Operation(
            summary = "Пополнение с помощью RabbitMQ",
            description = "Внесение денежной суммы (sum) на счет по ид (id) счета"
    )
    @PatchMapping("accounts/refill_with_rabbit/")
    public void refillMoneyByIdThroughRabbit(@RequestBody AccRequestDTO accRequestDTO) {
        template.convertAndSend("refill", accRequestDTO);
        log.info("отправлено сообщение refill с параметрами: {}", accRequestDTO);
    }
    @Operation(
            summary = "Списание с помощью RabbitMQ",
            description = "Списание денежной суммы (sum) со счета по ид (id) счета"
    )
    @PatchMapping("accounts/withdraw_with_rabbit")
    public void withdrawMoneyByIdThroughRabbit(@RequestBody AccRequestDTO accRequestDTO) {
        template.convertAndSend("withdraw", accRequestDTO);
        log.info("отправлено сообщение withdraw с параметрами: {}", accRequestDTO);
    }
    @Operation(
            summary = "Перевод с помощью RabbitMQ",
            description = "Перевод денежной суммы (transferSum) со счета (idFrom) на счет (idTo) по ид счетов"
    )
    @PatchMapping("accounts/transfer_with_rabbit")
    public void transferMoneyByIdThroughRabbit(@RequestBody AccRequestForTransferDTO accRequestForTransferDTO) {
        template.convertAndSend("transfer", accRequestForTransferDTO);
        log.info("отправлено сообщение transfer с параметрами: {}", accRequestForTransferDTO);
    }

}
