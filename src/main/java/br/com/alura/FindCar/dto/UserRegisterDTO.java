package br.com.alura.FindCar.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterDTO(
        @NotBlank(message = "O nome é obrigatório!")
        String name,

        @NotBlank(message = "O e-mail é obirgatório!")
        @Email(message = "O formato do e-mail esta incorreto!")
        String email,

        @NotBlank(message = "A senha é obrigatória!")
        String password
) {}
