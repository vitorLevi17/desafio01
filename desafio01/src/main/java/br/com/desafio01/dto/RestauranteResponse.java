package br.com.desafio01.dto;

import br.com.desafio01.entities.TipoCozinha;
public record RestauranteResponse(Long id,
                                  String nome,
                                  String endereco,
                                  String cep,
                                  TipoCozinha tipoCozinha,
                                  String horaFuncionamento,
                                  String contato,
                                  String nomeDono,
                                  String emailDono) {
}
