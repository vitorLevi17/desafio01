package br.com.desafio01.controller;

import br.com.desafio01.dto.CreateCardapioDTO;
import br.com.desafio01.dto.CreateItemCardapioDTO;
import br.com.desafio01.entities.Cardapio;
import br.com.desafio01.services.CardapioService;
import jakarta.validation.Valid;
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
    @PostMapping("restaurantes/cardapio/adicionar-item-cardapio")
    public ResponseEntity adicionarItemCardapio(@Valid @RequestBody CreateItemCardapioDTO createItemCardapioDTO){
        cardapioService.saveItemCardapio(createItemCardapioDTO);
        return ResponseEntity.ok("Item adicionado ao cardápio");
    }
    @DeleteMapping("restaurantes/cardapio/{id}")
    public ResponseEntity deletarCardapio(@PathVariable Long id){
        cardapioService.deleteCardapio(id);
        return ResponseEntity.ok("Cardápio excluido do restaurante");
    }
    @DeleteMapping("restaurantes/cardapio/remover-item-cardapio/{id}")
    public ResponseEntity deletarItemCardapio(@PathVariable Long id){
        cardapioService.deleteItemCardapio(id);
        return ResponseEntity.ok("Item excluido do cardápio");
    }


}
