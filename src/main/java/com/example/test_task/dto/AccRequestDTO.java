package com.example.test_task.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class AccRequestDTO {
    private Integer id;
    private BigDecimal sum;
}
