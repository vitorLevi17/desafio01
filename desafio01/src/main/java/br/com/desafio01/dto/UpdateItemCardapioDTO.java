package br.com.desafio01.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateItemCardapioDTO(
        @NotNull(message = "O id não pode ser nulo")
        Long id,
        @NotBlank(message = "O campo não pode ser nulo")
        String nome,
        @NotBlank(message = "O campo não pode ser nulo")
        String descricao,
        @Positive(message = "Insira valores positivos")
        @NotNull(message = "O campo não pode ser nulo")
        BigDecimal preco,
        @Pattern(regexp = "^(S|N)$",
                flags = Pattern.Flag.CASE_INSENSITIVE,
                message = "Valor inválido. Use apenas 'S' ou 'N'."
        )
        @NotBlank(message = "O campo não pode ser nulo")
        String viagemSN,
        String caminhoFoto

) {
}

