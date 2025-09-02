package br.com.desafio01.services;

import br.com.desafio01.dto.RestauranteResponse;
import br.com.desafio01.entities.Restaurante;
import br.com.desafio01.repository.RestaurantesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {
    private RestaurantesRepository repository;
    public RestauranteService(RestaurantesRepository repository) {
        this.repository = repository;
    }
    public List<RestauranteResponse> getRestaurantes(){
        var restaurantesList = repository.findAll();
        var restaurantes = responseRestauranteMetohd(restaurantesList);
        System.out.println(restaurantes);
        return restaurantes;
    }
    private List<RestauranteResponse> responseRestauranteMetohd(List<Restaurante> restaurantes){
        List<RestauranteResponse> responseList = restaurantes.stream().map(restaurante -> new RestauranteResponse(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getEndereco(),
                restaurante.getCep(),
                restaurante.getTipoCozinha(),
                restaurante.getHoraFuncionamento(),
                restaurante.getContato(),
                restaurante.getDono().getNome(),
                restaurante.getDono().getEmail()
        )).toList();
        return responseList;
    }
}
