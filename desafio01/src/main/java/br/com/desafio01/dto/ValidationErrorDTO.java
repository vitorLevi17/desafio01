package br.com.desafio01.dto;

import java.util.List;

public record ValidationErrorDTO(List<String> errors, int status) {
}
