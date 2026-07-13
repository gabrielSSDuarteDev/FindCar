package br.com.alura.FindCar;

import br.com.alura.FindCar.Pricnipal.Principal;
import br.com.alura.FindCar.model.DadosVeiculo;
import br.com.alura.FindCar.service.ConsumoAPI;
import br.com.alura.FindCar.service.ConverterDadosApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FindCarApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FindCarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		principal.exibirMenu();


	}
}
