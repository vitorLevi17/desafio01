package br.com.desafio01.dto;

import br.com.desafio01.entities.ItemCardapio;
import br.com.desafio01.entities.Restaurante;
import java.util.List;

public record CardapioResponse(
        Long id,
        String restauranteNome,
        String cardapioNome,
        List<ItemCardapioResponse> itens
) {
}
