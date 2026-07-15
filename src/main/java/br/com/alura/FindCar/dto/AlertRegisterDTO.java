package br.com.alura.FindCar.dto;

import java.math.BigDecimal;

public record AlertRegisterDTO(
        String fipeCode,
        String modelName,
        BigDecimal targetPrice,
        BigDecimal currentPrice
) {
}
