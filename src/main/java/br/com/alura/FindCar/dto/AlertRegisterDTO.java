package br.com.alura.FindCar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AlertRegisterDTO(

        @NotBlank(message = "O codigo FIPE é obrigatório")
        String fipeCode,

        @NotBlank(message = "O nome do modelo é Obrigatório")
        String modelName,

        @NotNull(message = "O preço alvo é obrigatório")
        @Positive(message = "O preço alvo deve ser maior que zero!")
        BigDecimal targetPrice,


        @NotNull(message = "O preço alvo é obrigatório")
        @Positive(message = "O preço alvo deve ser maior que zero!")
        BigDecimal currentPrice
) {
}
