package br.com.desafio01.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException (String mensagem){
        super(mensagem);
    }
}
