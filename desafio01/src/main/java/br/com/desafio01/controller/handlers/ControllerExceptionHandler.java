package br.com.desafio01.controller.handlers;

import br.com.desafio01.dto.ResourceNotFoundDTO;
import br.com.desafio01.dto.ValidationErrorDTO;
import br.com.desafio01.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFoundException(ResourceNotFoundException e){
        var status_code = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status_code.value()).body(new ResourceNotFoundDTO(e.getMessage(), status_code.value()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handlerResourceNotFoundException(MethodArgumentNotValidException e){
        var status_code = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for (var error: e.getBindingResult().getFieldErrors()){
            errors.add(error.getField() + " "+error.getDefaultMessage());
        }
        return ResponseEntity.status(status_code.value()).body(new ValidationErrorDTO(errors, status_code.value()));
    }
}