package br.com.alura.FindCar.service;

import br.com.alura.FindCar.service.IConverterDadosAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverterDadosApi implements IConverterDadosAPI {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try{
            return objectMapper.readValue(json, classe);
        }
        catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe){
        CollectionType Lista = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, classe);
        try {
            return objectMapper.readValue(json, Lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
