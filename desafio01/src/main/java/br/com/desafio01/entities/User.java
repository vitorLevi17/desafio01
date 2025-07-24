package br.com.desafio01.entities;

import br.com.desafio01.dto.LoginDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Date;

@Getter
@Setter
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
    private String nome;
    private String email;
    private String endereco;
    @UpdateTimestamp
    private Date dtAtualização;

    public boolean isLoginCorrect(LoginDto loginDto, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginDto.password(), this.password);
    }
}