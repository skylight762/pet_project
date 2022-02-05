package com.example.test_task.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {
    //настройка подключения к кролику
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setExchange("TransactionExchainge");
        return rabbitTemplate;
    }

    //кастомная очередь
    @Bean
    public Queue refillQueue() {
        return new Queue("RefillQueue", false);
    }

    @Bean
    public Queue withdrawQueue() {
        return new Queue("WithdrawQueue", false);
    }

    @Bean
    public Queue transferQueue() {
        return new Queue("TransferQueue", false);
    }
    //очередь для ошибок
    @Bean
    public Queue exceptionQueue(){
        return new Queue("ExceptionQueue",false);
    }
    //маршрутизатор
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("TransactionExchainge");
    }

    //маршрутизация
    //Binding для пополнения счета
    @Bean
    public Binding refillBinding() {
        return BindingBuilder.bind(refillQueue()).to(directExchange()).with("refill");
    }

    //Binding для списания со счета
    @Bean
    public Binding withdrawBinding() {
        return BindingBuilder.bind(withdrawQueue()).to(directExchange()).with("withdraw");
    }

    //Binding для перевода со счета на счет
    @Bean
    public Binding transferBinding() {
        return BindingBuilder.bind(transferQueue()).to(directExchange()).with("transfer");
    }

    //Binding для ошибок в переводах
    @Bean
    public Binding transferExceptionBinding() {
        return BindingBuilder.bind(exceptionQueue()).to(directExchange()).with("transfer_error");
    }
}