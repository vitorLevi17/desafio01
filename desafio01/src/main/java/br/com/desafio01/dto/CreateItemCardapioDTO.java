package br.com.desafio01.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateItemCardapioDTO(
        @NotBlank(message = "O campo não pode ser nulo")
        String nomeItem,
        @NotBlank(message = "O campo não pode ser nulo")
        String descricao,
        @Positive(message = "Insira um valor positivo")
        @NotNull(message = "O campo não pode ser nulo")
        BigDecimal preco,
        @Pattern(regexp = "^(S|N)$",
                flags = Pattern.Flag.CASE_INSENSITIVE,
                message = "Valor inválido. Use apenas 'S' ou 'N'."
        )
        @NotBlank(message = "O campo não pode ser nulo")
        String viagemSN,
        String caminhoFoto,
        Long cardapioId
) {
}
