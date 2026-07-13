package br.com.alura.FindCar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record modelos(List<Dados> modelos) {
}
