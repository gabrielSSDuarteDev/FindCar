package br.com.alura.FindCar.model;

public enum TipoVeiculo {
    CARROS("carros"),
    MOTOS("motos"),
    CAMINHOES("caminhoes");


    private final String endpoint;

    TipoVeiculo(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndPoint(){
        return endpoint;
    }
}
