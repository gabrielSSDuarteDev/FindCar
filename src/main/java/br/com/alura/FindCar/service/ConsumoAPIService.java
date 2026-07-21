package br.com.alura.FindCar.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Component
public class ConsumoAPIService {


    public String obterDados(String codigoURL) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(codigoURL))
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        }catch (IOException e){
            throw new IOException(e);
        }catch (InterruptedException e){
            throw new InterruptedException();
        }

        return response.body();
    }
}
