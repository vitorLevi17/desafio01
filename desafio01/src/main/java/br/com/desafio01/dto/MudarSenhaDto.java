package br.com.desafio01.dto;

import jakarta.validation.constraints.NotBlank;

public record MudarSenhaDto(@NotBlank(message = "Está vazio") String username,
                            @NotBlank(message = "Está vazio") String last_password,
                            @NotBlank(message = "Está vazio") String password,
                            @NotBlank(message = "Está vazio") String confirm_password) {
}
