package br.com.desafio01.controller;

import br.com.desafio01.dto.CreateCardapioDTO;
import br.com.desafio01.entities.Cardapio;
import br.com.desafio01.services.CardapioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardapioController {

    private CardapioService cardapioService;

    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @GetMapping("restaurantes/cardapio/{id}")
    public ResponseEntity getCardapioRestaurante(@PathVariable Long id){
        var cardapio = cardapioService.getCardapioRestaurante(id);
        return ResponseEntity.ok(cardapio);
    }
    @PostMapping("restaurantes/cardapio/")
    public ResponseEntity criarCardapioRestaurante(@RequestBody CreateCardapioDTO createCardapioDTO){
        cardapioService.saveCardapio(createCardapioDTO);
        return ResponseEntity.ok("Cardápio criado com sucesso");
    }

}
