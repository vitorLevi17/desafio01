package br.com.desafio01.services;

import br.com.desafio01.dto.CreateRestauranteDTO;
import br.com.desafio01.dto.RestauranteResponse;
import br.com.desafio01.entities.Restaurante;
import br.com.desafio01.entities.TipoCozinha;
import br.com.desafio01.entities.User;
import br.com.desafio01.repository.RestaurantesRepository;
import br.com.desafio01.repository.TipoCozinhaRepository;
import br.com.desafio01.repository.UserRepository;
import br.com.desafio01.services.exceptions.ConflictException;
import br.com.desafio01.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
public class RestauranteService {
    private RestaurantesRepository repository;
    private UserRepository userRepository;
    private TipoCozinhaRepository tipoCozinhaRepository;

    public RestauranteService(RestaurantesRepository repository, UserRepository userRepository, TipoCozinhaRepository tipoCozinhaRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    public List<RestauranteResponse> getRestaurantes(){
        var restaurantesList = repository.findAll();
        var restaurantes = responseRestauranteMetohd(restaurantesList);
        System.out.println(restaurantes);
        return restaurantes;
    }
    public Restaurante saveRestaurante(CreateRestauranteDTO createRestauranteDTO){
        User user = userRepository.findById(createRestauranteDTO.dono()).
                orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        if (user.getRole().getId() != 2){
            throw new ResourceNotFoundException("O usuário inserido não é dono de restaurante");
        }
        TipoCozinha tipoCozinha = tipoCozinhaRepository.findById(createRestauranteDTO.tipoCozinha())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de cozinha não encontrado"));
        if (repository.findByNomeAndCep(createRestauranteDTO.nome(),createRestauranteDTO.cep()).isPresent()){
            throw new ConflictException("O Restaurante já foi inserido");
        }
        Restaurante restaurante = new Restaurante(createRestauranteDTO,
                tipoCozinha,
                user);
        return repository.save(restaurante);
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
