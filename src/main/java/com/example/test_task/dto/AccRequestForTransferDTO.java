package com.example.test_task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Schema(name = "AccRequestForTransferDTO", description = "DTO для перевода со счета на счет")
public class AccRequestForTransferDTO {

    @Schema(name = "idFrom", description = "номер счета с которого совершается перевод")
    private Integer idFrom;

    @Schema(name = "idTo", description = "номер счета на который совершается перевод")
    private Integer idTo;

    @Schema(name = "transferSum", description = "сумма которую необходимо списать с первого счета и положить на второй")
    private BigDecimal transferSum;
}
