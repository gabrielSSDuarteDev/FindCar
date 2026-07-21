package br.com.alura.FindCar.service;


import br.com.alura.FindCar.model.Alert;
import br.com.alura.FindCar.repository.IAlertRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceSchedulerService {

    private final IAlertRepository alertRepository;
    private final ConsumoAPI consumoAPI;
    private final ConverterDadosApi converteDados;


    public PriceSchedulerService(IAlertRepository alertRepository,
                                 ConsumoAPI consumoAPI,
                                 ConverterDadosApi converteDados) {
        this.alertRepository = alertRepository;
        this.consumoAPI = consumoAPI;
        this.converteDados = converteDados;
    }



    @Scheduled(fixedRate = 30000)
    public void monitorPrices(){
        System.out.println("\n [BOT FindCar] Iniciando varredura automatizada de preço...");

        List<Alert> alerts = alertRepository.findAll();

        if(alerts.isEmpty()){
            System.out.println("\n [BOT FindCar] Nenhum Alerta cadastrado no banco de dados");
        }

        for(Alert alert : alerts){
            try{
                System.out.println("\n Verificando Alerta ID " + alert.getId() + " - Modelo: " + alert.getModelName());

            }catch(Exception e){
                System.out.println("\n Erro ao processar o Alerta " + alert.getId() + ":" + e.getMessage());
            }
        }
        System.out.println("\n [BOT FindCar] Varredura Concluída");
    }
}
