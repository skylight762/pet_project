package com.example.test_task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Schema(name = "AccRequestDTO", description = "ДТО для запроса суммы перевода")
public class AccRequestDTO implements Serializable {

    @Schema(name = "id", description = "id счета для проведения операции")
    private Integer id;

    @Schema(name = "sum", description = "сумма перевода")
    private BigDecimal sum;

}
