package br.com.desafio01.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "tipo_cozinha")
@Getter
public class TipoCozinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;

}
