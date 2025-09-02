package br.com.desafio01.controller;

import br.com.desafio01.dto.CreateRestauranteDTO;
import br.com.desafio01.dto.RestauranteResponse;
import br.com.desafio01.dto.UpdateRestauranteDTO;
import br.com.desafio01.services.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantesController {
    private RestauranteService restauranteService;
    public RestaurantesController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }
    @GetMapping("/restaurantes")
    public ResponseEntity<List<RestauranteResponse>> getRestaurantes(){
        var restaurantes = restauranteService.getRestaurantes();
        return ResponseEntity.ok(restaurantes);
    }
    @PostMapping("/restaurantes")
    public ResponseEntity postRestaurante(@Valid @RequestBody CreateRestauranteDTO createRestauranteDTO){
        restauranteService.saveRestaurante(createRestauranteDTO);
        return ResponseEntity.ok("Restaurente "+ createRestauranteDTO.nome() +" cadastrado com sucesso");
    }
    @PutMapping("/restaurantes")
    public ResponseEntity updateRestaurante(@Valid @RequestBody UpdateRestauranteDTO updateRestauranteDTO){
        restauranteService.updateRestaurante(updateRestauranteDTO);
        return ResponseEntity.ok("Restaurante "+ updateRestauranteDTO.id() +" alterado com sucesso");
    }
    @DeleteMapping("/restaurantes/{id}")
    public ResponseEntity deleteRestaurante(@PathVariable Long id){
        restauranteService.deleteRestaurante(id);
        return ResponseEntity.ok("Restaurante "+ id + " foi deletado com sucesso");
    }
}
