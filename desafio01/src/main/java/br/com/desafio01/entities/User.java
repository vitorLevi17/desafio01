package br.com.desafio01.entities;

import br.com.desafio01.dto.LoginDto;
import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;
//@Lombok
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username; //UNIQUE
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public boolean isLoginCorrect(LoginDto loginDto, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginDto.password(), this.password);
    }
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
