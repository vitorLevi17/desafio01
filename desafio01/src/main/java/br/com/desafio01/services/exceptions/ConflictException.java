package br.com.desafio01.services.exceptions;

public class ConflictException extends ResourceNotFoundException{
    public ConflictException(String mensagem) {
        super(mensagem);
    }
}
