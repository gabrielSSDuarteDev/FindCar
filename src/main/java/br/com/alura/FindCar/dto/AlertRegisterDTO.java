package br.com.alura.FindCar.dto;

import java.math.BigDecimal;

public record AlertRegisterDTO(

        @NotBlank(message = "")
        String fipeCode,
        String modelName,
        BigDecimal targetPrice,
        BigDecimal currentPrice
) {
}
