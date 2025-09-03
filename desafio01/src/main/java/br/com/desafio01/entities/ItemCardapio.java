package br.com.desafio01.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Table(name = "item_cardapio")
@Getter
public class ItemCardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean viagemSN;
    private String caminhoFoto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardapio_id")
    private Cardapio cardapio;

    public ItemCardapio() {
    }
}
