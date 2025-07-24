package br.com.desafio01.controller;

import br.com.desafio01.entities.User;
import br.com.desafio01.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    private UserService userService;
    public UsuarioController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/usuarios")
    public ResponseEntity<List<User>> Usuarios(){
        var usuarios = userService.findAllUsers();
        return ResponseEntity.ok(usuarios);
    }

}
