package com.example.test_task.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionDTO {

    private Integer id;
    private Integer accountid;
    private String operationType;
    private BigDecimal sum;

}
