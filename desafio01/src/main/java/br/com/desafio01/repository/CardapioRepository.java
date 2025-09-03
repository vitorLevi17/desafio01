package br.com.desafio01.repository;

import br.com.desafio01.entities.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardapioRepository extends JpaRepository<Cardapio,Long> {

    Optional<Cardapio> findByRestauranteId(Long id);
}
