package br.com.desafio01.controller;

import br.com.desafio01.services.CardapioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardapioController {

    private CardapioService cardapioService;

    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @GetMapping("restaurantes/cardapio/{id}")
    public ResponseEntity CardapioResponse(@PathVariable Long id){
        var cardapio = cardapioService.getCardapioRestaurante(id);
        return ResponseEntity.ok(cardapio);
    }
}
