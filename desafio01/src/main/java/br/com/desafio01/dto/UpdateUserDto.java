package br.com.desafio01.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateUserDto(
        @NotNull(message = "O campo não pode ser nulo")Long id,
        @NotBlank(message = "O campo não pode ser nulo")String nome,
        @NotBlank(message = "O campo não pode ser nulo") @Email String email,
        @NotBlank @Pattern(regexp = "Dono|Cliente",message = "O campo deve ter os valores (Dono) ou (Cliente)")String role,
        String endereco
) {
}
