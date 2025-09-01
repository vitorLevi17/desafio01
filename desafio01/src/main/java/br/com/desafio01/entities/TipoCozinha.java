package br.com.desafio01.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_cozinha")
public class TipoCozinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;

}
