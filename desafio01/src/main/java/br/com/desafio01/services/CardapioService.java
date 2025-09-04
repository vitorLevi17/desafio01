package br.com.desafio01.services;

import br.com.desafio01.dto.CardapioResponse;
import br.com.desafio01.dto.CreateCardapioDTO;
import br.com.desafio01.dto.CreateItemCardapioDTO;
import br.com.desafio01.dto.ItemCardapioResponse;
import br.com.desafio01.entities.Cardapio;
import br.com.desafio01.entities.ItemCardapio;
import br.com.desafio01.entities.Restaurante;
import br.com.desafio01.repository.CardapioRepository;
import br.com.desafio01.repository.ItemCardapioRepository;
import br.com.desafio01.repository.RestaurantesRepository;
import br.com.desafio01.services.exceptions.ConflictException;
import br.com.desafio01.services.exceptions.ResourceNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class CardapioService {
    private CardapioRepository cardapioRepository;
    private ItemCardapioRepository item;
    private RestaurantesRepository restaurantesRepository;

    public CardapioService(CardapioRepository cardapioRepository, ItemCardapioRepository item, RestaurantesRepository restaurantesRepository) {
        this.cardapioRepository = cardapioRepository;
        this.item = item;
        this.restaurantesRepository = restaurantesRepository;
    }

    public CardapioResponse getCardapioRestaurante(Long id) {
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

    public Cardapio saveCardapio(CreateCardapioDTO createCardapioDTO) {
        Restaurante restaurante = restaurantesRepository.findById(createCardapioDTO.restauranteId()).
                orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));
        if (cardapioRepository.findByRestauranteId(restaurante.getId()).isPresent()) {
            throw new ConflictException("O restaurante já possui um cardápio");
        }
        Cardapio cardapio = new Cardapio(createCardapioDTO.cardapioNome(), restaurante);
        return cardapioRepository.save(cardapio);
    }

    public ItemCardapio saveItemCardapio(CreateItemCardapioDTO createItemCardapioDTO) {
        Cardapio cardapio = cardapioRepository.findById(createItemCardapioDTO.cardapioId()).
                orElseThrow(() -> new ResourceNotFoundException("Cardápio não encontrado"));
        var viagem = viagemSN(createItemCardapioDTO.viagemSN());
        ItemCardapio itemCardapio = new ItemCardapio(createItemCardapioDTO, viagem, cardapio);
        return item.save(itemCardapio);
    }
    public void deleteCardapio(Long id){
        var cardapio  = cardapioRepository.findById(id);
        if (cardapio.isPresent()){
            cardapioRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Cardápio não encontrado");
        }
    }
    public void deleteItemCardapio(Long id){
        var itemCardapio = item.findById(id);
        if (itemCardapio.isPresent()){
            item.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Item não encontrado no cardápio");
        }
    }
    private Boolean viagemSN(String sn) {
        if (sn.equalsIgnoreCase("S")) {
            return TRUE;
        } else {
            return FALSE;
        }
    }
}