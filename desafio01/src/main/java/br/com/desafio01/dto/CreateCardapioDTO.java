package br.com.desafio01.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCardapioDTO(
        @NotBlank(message = "O campo não pode ser nulo")
        String cardapioNome,
        @NotNull(message = "O campo não pode ser nulo")
        Long restauranteId
) {
}
