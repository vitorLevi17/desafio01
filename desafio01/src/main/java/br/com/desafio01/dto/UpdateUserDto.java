package br.com.desafio01.dto;

import br.com.desafio01.entities.Role;
public record UpdateUserDto(
        Long id,
        String nome,
        String email,
        String role,
        String endereco
) {
}
