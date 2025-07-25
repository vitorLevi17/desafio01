package br.com.desafio01.dto;

import br.com.desafio01.entities.Role;
import java.util.Date;

public record UserResponse(Long id,String nome,String username,String email,Role role, String endereco,Date dtAtualização) {
}