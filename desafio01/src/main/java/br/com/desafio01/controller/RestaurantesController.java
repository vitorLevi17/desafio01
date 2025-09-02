package br.com.desafio01.controller;

import br.com.desafio01.dto.RestauranteResponse;
import br.com.desafio01.entities.Restaurante;
import br.com.desafio01.services.RestauranteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
