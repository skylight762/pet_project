package com.example.test_task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Schema(name = "AccRequestDTO", description = "ДТО для запроса суммы перевода")
public class AccRequestDTO {

    @Schema(name = "id", description = "id счета для проведения операции")
    private Integer id;

    @Schema(name = "sum", description = "сумма перевода")
    private BigDecimal sum;

}
