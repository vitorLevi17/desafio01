package br.com.desafio01.dto;

import jakarta.validation.constraints.*;

import java.time.LocalTime;

public record CreateRestauranteDTO(
        @NotBlank(message = "O campo não pode ser nulo") String nome,
        @NotBlank(message = "O campo não pode ser nulo") String endereco,
        @Pattern(regexp = "\\d{8}", message = "O CEP é inválido")
        @NotBlank(message = "O campo CEP não pode ser nulo")
        String cep,
        @Positive(message = "O campo não pode ser negativo")
        @NotNull(message = "O campo não pode ser nulo")
        Long tipoCozinha,
        @NotNull LocalTime horarioAbertura,
        @NotNull LocalTime horarioFechamento,
        @Pattern(regexp = "^\\d{10,11}$", message = "O número de contato deve conter apenas números")
        @NotBlank(message = "O campo contato não pode ser nulo")
        String contato,
        @Positive(message = "O campo não pode ser negativo")
        @NotNull(message = "O campo não pode ser nulo")
        Long dono
){
}
