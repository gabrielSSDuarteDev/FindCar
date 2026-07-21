package br.com.alura.FindCar;

import br.com.alura.FindCar.Principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FindCarApplication  {

	public static void main(String[] args) {
		SpringApplication.run(FindCarApplication.class, args);
	}


}
