package br.com.desafio01.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo_usuario; //UNIQUE

    public enum Values{
        CLIENTE(1),
        DONO(2);

        long id;

        Values(long id){
            this.id=id;
        }

    }
    public Long getId() {
        return id;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

}
