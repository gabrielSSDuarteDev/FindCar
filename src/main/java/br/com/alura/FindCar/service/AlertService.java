package br.com.alura.FindCar.service;


import br.com.alura.FindCar.dto.AlertRegisterDTO;
import br.com.alura.FindCar.model.Alert;
import br.com.alura.FindCar.model.User;
import br.com.alura.FindCar.repository.IAlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final IAlertRepository alertRepository;



    public AlertService(IAlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public void createAlert(AlertRegisterDTO dto, User authenticatedUser) {
        Alert newAlert = new Alert(
                authenticatedUser,
                dto.fipeCode(),
                dto.modelName(),
                dto.targetPrice(),
                dto.currentPrice()
        );

        alertRepository.save(newAlert);
    }

    public List<Alert> listAlertByUser(User authenticatedUser){
        return alertRepository.findByUser(authenticatedUser);
    }








}
