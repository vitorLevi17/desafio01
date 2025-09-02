package br.com.desafio01.repository;

import br.com.desafio01.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantesRepository extends JpaRepository<Restaurante,Long> {

    Optional<Restaurante> findByNomeAndCep(String nome,String cep);
}
