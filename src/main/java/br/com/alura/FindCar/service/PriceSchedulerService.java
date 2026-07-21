package br.com.alura.FindCar.service;


import br.com.alura.FindCar.model.Alert;
import br.com.alura.FindCar.repository.IAlertRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PriceSchedulerService {

    private final IAlertRepository alertRepository;
    private final ConsumoAPIService consumoAPIService;
    private final ConverterDadosApiServiceService converteDados;
    @Value("${fipe.api.base-url}")
    private String baseUrl;


    public PriceSchedulerService(IAlertRepository alertRepository,
                                 ConsumoAPIService consumoAPIService,
                                 ConverterDadosApiServiceService converteDados) {
        this.alertRepository = alertRepository;
        this.consumoAPIService = consumoAPIService;
        this.converteDados = converteDados;
    }



    @Scheduled(initialDelay = 5000,fixedRate = 30000)
    @Transactional
    public void checkAndUpdatePrices(){
        System.out.println("\n ---->> [BOT FindCar]: INICIANDO VERFICAÇÃO DE PREÇOS <<---- ");

        List<Alert> alerts = alertRepository.findAll();
        System.out.println("---->> Alertas Encontrados no Banco de Dados <<----");
        record DataFipeValue(String valor, String Marca, String Modelo, Integer anoModelo){}

        if(alerts.isEmpty()){
            System.out.println("\n ---->> [BOT FindCar] Nenhum Alerta cadastrado no banco de dados <<-----");
        }

        for(Alert alert : alerts){
            try{
                System.out.println("\n ---->> Verificando Alerta ID " + alert.getId() + " - Modelo: " + alert.getModelName() + "<<-----");

                String finalUrl= UriComponentsBuilder.fromHttpUrl(baseUrl)
                        .pathSegment("marcas",alert.getBrandCode())
                        .pathSegment("modelos",alert.getModelCode())
                        .pathSegment("anos",alert.getYearCode())
                        .toUriString();

                System.out.println("↳ Consultando URL FIPE ");
                String json = consumoAPIService.obterDados(finalUrl);
                DataFipeValue dataValue = converteDados.obterDados(json, DataFipeValue.class);

                BigDecimal currentPrice = convertToBigDecimal(dataValue.valor());
                alert.setCurrentPrice(currentPrice);

                System.out.println(" ↳ Preço Alvo no Banco: R$ " + alert.getTargetPrice());
                System.out.println(" ↳ Preço Atual na FIPE: R$ " + currentPrice);

                if (currentPrice.compareTo(alert.getTargetPrice()) <= 0){
                    System.out.println("----> [ALERTA DISPARADO!!!] <<---- | O veículo "  + alert.getModelName()
                                        + "atingiu o preço desejado");


                    System.out.println("\n Preço Alvo: R$ " + alert.getTargetPrice() +
                                        "| Preço Atual FIPE: R$ " + currentPrice);
                }else {
                    System.out.println("  [Aguardando] Preço atual ainda está acima do alvo.");
                }

                alertRepository.save(alert);
                System.out.println(" ↳ Registro atualizado no PostgreSQL com sucesso.");


            }catch(Exception e){
                System.out.println("\n Erro ao processar o Alerta " + alert.getId() + ":" + e.getMessage());
            }
        }
        System.out.println("\n ---->> [BOT FindCar] Varredura Concluída <<----");
    }

    private BigDecimal convertToBigDecimal(String fipeValue) {
       if (fipeValue == null || fipeValue.isBlank()){
           return BigDecimal.ZERO;
       }
       String clear = fipeValue.replace("R$", "")
                                .replace(" ", "")
               .replace(".","")
               .replace(",",".");
       return new BigDecimal(clear);
    }
}
