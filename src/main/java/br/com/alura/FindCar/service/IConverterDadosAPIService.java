package br.com.alura.FindCar.service;

import java.util.List;

public interface IConverterDadosAPIService {
    <T> T obterDados(String json, Class<T> classe) ;
    <T> List<T> obterLista(String json, Class<T> classe) ;
}
