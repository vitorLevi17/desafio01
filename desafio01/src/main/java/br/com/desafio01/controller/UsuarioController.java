package br.com.desafio01.controller;

import br.com.desafio01.services.UserService;
import br.com.desafio01.dto.UserResponse;
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
    public ResponseEntity<List<UserResponse>> Usuarios(){
        var usuarios = userService.findAllUsers();

        List<UserResponse> responseList = usuarios.stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getNome(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole(),
                        user.getEndereco(),
                        user.getDtAtualização()
                ))
                .toList();
        return ResponseEntity.ok(responseList);
    }

}
