package br.com.desafio01.repository;

import br.com.desafio01.entities.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapio,Long> {

}
