package com.example.test_task.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Data
@Entity(name = "TRANSACTION")
@Accessors(chain = true)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer id;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "operation_name")
    private String operationName;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "start_time")
    private ZonedDateTime startTime = ZonedDateTime.now();

    @Column(name = "end_time")
    private ZonedDateTime endTime;
}
