package br.com.alura.FindCar.controller;


import br.com.alura.FindCar.dto.AlertRegisterDTO;
import br.com.alura.FindCar.model.User;
import br.com.alura.FindCar.service.AlertService;
import br.com.alura.FindCar.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alerts")
public class AlertController {
    private final AlertService alertService;
    private final UserService userService;

    public AlertController(AlertService alertService, UserService userService) {
        this.alertService = alertService;
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<String> createAlert(@RequestBody  AlertRegisterDTO dto) {
        try {
            User user = userService.loadUserByUsername(dto.modelName());
             alertService.createAlert(dto);

            return ResponseEntity.status(HttpStatus.CREATED).body("Alerta de preço criado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
