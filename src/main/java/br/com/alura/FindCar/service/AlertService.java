package br.com.alura.FindCar.service;


import br.com.alura.FindCar.dto.AlertRegisterDTO;
import br.com.alura.FindCar.model.Alert;
import br.com.alura.FindCar.model.TipoVeiculo;
import br.com.alura.FindCar.model.User;
import br.com.alura.FindCar.repository.IAlertRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static java.util.Arrays.stream;

@Service
public class AlertService {

    private final IAlertRepository alertRepository;
    private final ConsumoAPIService consumoAPIService;
    private final ConverterDadosApiServiceService converterDadosApiService;

    @Value("${fipe.api.base-url}")
    private  String baseUrl;



    public AlertService(IAlertRepository alertRepository, ConsumoAPIService consumoAPIService, ConverterDadosApiServiceService converterDadosApiService) {
        this.alertRepository = alertRepository;
        this.consumoAPIService = consumoAPIService;
        this.converterDadosApiService = converterDadosApiService;
    }

    public void createAlert(AlertRegisterDTO dto, User authenticatedUser) {
        String yearCodeFipe = resolveCodeYearFipe(
                dto.typeVehicle(),
                dto.brandCode(),
                dto.modelCode(),
                dto.yearCode()
        );
        Alert newAlert = new Alert(
                dto.typeVehicle(),
                authenticatedUser,
                dto.fipeCode(),
                dto.modelName(),
                dto.targetPrice(),
                dto.currentPrice(),
                dto.brandCode(),
                dto.modelCode(),
                yearCodeFipe
        );
        alertRepository.save(newAlert);
    }



    private String resolveCodeYearFipe(@NotNull(message = "O tipo do veículo é obrigatório (CARROS,MOTOS ou CAMINHOES)") TipoVeiculo tipoVeiculo,
                                       @NotNull(message = ("O código da marca é obrigatório")) String brandCode,
                                       @NotBlank(message = "O código do modelo é Obrigatório") String modelCode,
                                       @NotBlank(message = "O código do ano é Obrigatório") String yearInput) {
        if(yearInput != null && yearInput.contains("-")) {
            return yearInput;
        }
        record DadosAno(String code, String name){}

        try{

            String urlAnos = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .pathSegment(tipoVeiculo.getEndPoint())
                    .pathSegment("marcas",brandCode)
                    .pathSegment("modelos",modelCode)
                    .pathSegment("anos")
                    .toUriString();


            String json = consumoAPIService.obterDados(urlAnos);
            List<DadosAno> yearsAvailable = converterDadosApiService.obterLista(json, DadosAno.class );

             return yearsAvailable.stream()
                    .filter(a -> a.code.startsWith(yearInput) || a.name.startsWith(yearInput))
                    .map(DadosAno::code)
                    .findFirst()
                    .orElse(yearInput);

        }catch(Exception e){
            return yearInput;

        }
    }

    public List<Alert> listAlertByUser(User authenticatedUser){
        return alertRepository.findByUser(authenticatedUser);
    }








}
