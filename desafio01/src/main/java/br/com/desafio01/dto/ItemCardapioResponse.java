package br.com.desafio01.dto;

import java.math.BigDecimal;

public record ItemCardapioResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean viagemSN,
        String caminhoFoto
) {
}
