package br.com.desafio01.dto;

import br.com.desafio01.entities.Role;
public record CreateUserDto(String username,String password,String role,String nome,String email,String endereco) {
}