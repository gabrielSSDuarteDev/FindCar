package br.com.alura.FindCar.dto;

import br.com.alura.FindCar.model.TipoVeiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AlertRegisterDTO(

        @NotNull(message = ("O código da marca é obrigatório"))
        String brandCode,

        @NotBlank(message = "O código do modelo é Obrigatório")
        String modelCode,

        @NotBlank(message = "O código do ano é Obrigatório")
        String yearCode,

        @NotNull(message = "O tipo do veículo é obrigatório (CARROS,MOTOS ou CAMINHOES)")
        TipoVeiculo typeVehicle,

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
