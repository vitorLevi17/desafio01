package br.com.desafio01.entities;

import br.com.desafio01.dto.CreateItemCardapioDTO;
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
    public ItemCardapio(CreateItemCardapioDTO createItemCardapioDTO,Boolean viagemSN, Cardapio cardapio) {
        this.nome = createItemCardapioDTO.nomeItem();
        this.descricao = createItemCardapioDTO.descricao();
        this.preco = createItemCardapioDTO.preco();
        this.viagemSN = viagemSN;
        this.caminhoFoto = createItemCardapioDTO.caminhoFoto();
        this.cardapio = cardapio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setViagemSN(boolean viagemSN) {
        this.viagemSN = viagemSN;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
}
