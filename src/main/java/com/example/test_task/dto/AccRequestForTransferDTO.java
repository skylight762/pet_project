package com.example.test_task.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class AccRequestForTransferDTO {
    private Integer idFrom;
    private Integer idTo;
    private BigDecimal transferSum;
}
