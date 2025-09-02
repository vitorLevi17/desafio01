package br.com.desafio01.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserDto(@NotBlank(message = "O campo não pode ser nulo") String username,
                            @NotBlank(message = "O campo não pode ser nulo")String password,
                            @NotBlank @Pattern(regexp = "Dono|Cliente",message = "O campo deve ter os valores (Dono) ou (Cliente)") String role,
                            @NotBlank(message = "O campo não pode ser nulo") String nome,
                            @NotBlank @Email(message = "Formato de email inválido") String email,
                            String endereco) {
}