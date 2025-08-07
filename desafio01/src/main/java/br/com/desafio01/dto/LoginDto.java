package br.com.desafio01.dto;

import jakarta.validation.constraints.NotBlank;
public record LoginDto(@NotBlank(message = "Está vazio") String username,
                       @NotBlank(message = "Está vazio") String password) {
}
