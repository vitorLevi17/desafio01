package br.com.desafio01.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCardapioDTO(@NotNull(message = "O id não pode ser nulo") Long id,
                                @NotBlank(message = "O novo nome não pode ser nulo")String novoNome) {
}
