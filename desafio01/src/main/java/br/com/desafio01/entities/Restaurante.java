package br.com.desafio01.entities;

import br.com.desafio01.dto.CreateRestauranteDTO;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalTime;

@Entity
@Table(name = "restaurante")
@Getter
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private String cep;
    @ManyToOne
    @JoinColumn(name = "tipo_cozinha_id", referencedColumnName = "id")
    private TipoCozinha tipoCozinha;
    private String horaFuncionamento;
    private LocalTime horaAbertura;
    private LocalTime horaFechamento;
    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User dono;
    private String contato;
    public Restaurante() {
    }
    public Restaurante(CreateRestauranteDTO createRestauranteDTO,
                       TipoCozinha tipoCozinha,
                       User user) {
        this.nome = createRestauranteDTO.nome();
        this.endereco = createRestauranteDTO.endereco();
        this.cep = createRestauranteDTO.cep();
        this.tipoCozinha = tipoCozinha;
        this.horaFuncionamento = createRestauranteDTO.horarioAbertura() +" - " + createRestauranteDTO.horarioFechamento();
        this.horaAbertura = createRestauranteDTO.horarioAbertura();
        this.horaFechamento = createRestauranteDTO.horarioFechamento();
        this.dono = user;
        this.contato = createRestauranteDTO.contato();
    }
}
