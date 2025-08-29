package br.com.desafio01.entities;

import jakarta.persistence.*;

//LOMBOK
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoUsuario; //UNIQUE

    public enum Values{
        CLIENTE(1),
        DONO(2),
        ADMIN(3);

        long id;

        Values(long id){
            this.id=id;
        }

    }
    public Long getId() {
        return id;
    }

    @Column(name = "tipo_usuario")
    public String getTipoUsuario() {
        return tipoUsuario;
    }

}
