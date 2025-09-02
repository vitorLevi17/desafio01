package br.com.desafio01.repository;

import br.com.desafio01.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantesRepository extends JpaRepository<Restaurante,Long> {
}
