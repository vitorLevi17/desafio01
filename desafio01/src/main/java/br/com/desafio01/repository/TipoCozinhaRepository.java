package br.com.desafio01.repository;

import br.com.desafio01.entities.TipoCozinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCozinhaRepository extends JpaRepository<TipoCozinha,Long> {
}
