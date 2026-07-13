package br.com.alura.FindCar.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(
        @JsonProperty("Valor")String valor,
        @JsonAlias("Marca")String marca,
        @JsonAlias ("Modelo")String modelo,
        @JsonAlias ("AnoModelo")Integer ano,
        @JsonAlias ("Combustivel")String fuel

) {

}
