package br.com.alura.FindCar.model;

import br.com.alura.FindCar.model.Dados;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record modelos(List<Dados> modelos) {
}
