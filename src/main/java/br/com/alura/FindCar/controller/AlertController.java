package br.com.alura.FindCar.controller;


import br.com.alura.FindCar.dto.AlertRegisterDTO;
import br.com.alura.FindCar.model.User;
import br.com.alura.FindCar.service.AlertService;
import br.com.alura.FindCar.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alerts")
public class AlertController {
    private final AlertService alertService;
    private final UserService userService;

    public AlertController(AlertService alertService, UserService userService) {
        this.alertService = alertService;
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<?> listAllAlerts(@RequestHeader("X-User-Id") Long userId) {
        try {
            User userAuthenticated = userService.findById(userId);
            var alerts = alertService.listAlertByUser(userAuthenticated);
            return ResponseEntity.ok(alerts);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao listar os alertas de preço");
        }
    }


    @PostMapping
    public ResponseEntity<String> createAlert(@RequestBody @Valid AlertRegisterDTO dto,
                                              @RequestHeader("X-User-Id") Long UserId) {
        try {
            User user = userService.findById(UserId);
             alertService.createAlert(dto,user);

            return ResponseEntity.status(HttpStatus.CREATED).body("Alerta de preço criado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
