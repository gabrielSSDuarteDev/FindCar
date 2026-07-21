package br.com.alura.FindCar.service;


import br.com.alura.FindCar.dto.AlertRegisterDTO;
import br.com.alura.FindCar.model.Alert;
import br.com.alura.FindCar.model.TipoVeiculo;
import br.com.alura.FindCar.model.User;
import br.com.alura.FindCar.repository.IAlertRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final IAlertRepository alertRepository;



    public AlertService(IAlertRepository alertRepository) {
        this.alertRepository = alertRepository;
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
                dto.yearCode()
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
    }

    public List<Alert> listAlertByUser(User authenticatedUser){
        return alertRepository.findByUser(authenticatedUser);
    }








}
