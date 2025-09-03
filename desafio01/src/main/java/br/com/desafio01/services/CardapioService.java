package br.com.desafio01.services;

import br.com.desafio01.dto.CardapioResponse;
import br.com.desafio01.dto.ItemCardapioResponse;
import br.com.desafio01.entities.Cardapio;
import br.com.desafio01.entities.ItemCardapio;
import br.com.desafio01.repository.CardapioRepository;
import br.com.desafio01.repository.ItemCardapioRepository;
import br.com.desafio01.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardapioService {
    private CardapioRepository cardapioRepository;
    private ItemCardapioRepository item;
    public CardapioService(CardapioRepository cardapioRepository, ItemCardapioRepository item) {
        this.cardapioRepository = cardapioRepository;
        this.item = item;
    }
    public CardapioResponse getCardapioRestaurante(Long id){
        Cardapio cardapio = cardapioRepository.findByRestauranteId(id).orElseThrow(()
                -> new ResourceNotFoundException("Restaurante não encontrado"));

        List<ItemCardapioResponse> itensResponse = cardapio.getItens().stream()
                .map(item -> new ItemCardapioResponse(item.getId(),
                        item.getNome(),
                        item.getDescricao(),
                        item.getPreco(),
                        item.isViagemSN(),
                        item.getCaminhoFoto()
                )).toList();

        CardapioResponse cardapioResponse = new CardapioResponse(
                cardapio.getId(),
                cardapio.getRestaurante().getNome(),
                cardapio.getNome(),
                itensResponse
        );
        return cardapioResponse;
    }
}
