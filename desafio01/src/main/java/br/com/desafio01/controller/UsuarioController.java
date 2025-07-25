package br.com.desafio01.controller;

import br.com.desafio01.dto.CreateUserDto;
import br.com.desafio01.entities.User;
import br.com.desafio01.services.RoleService;
import br.com.desafio01.services.UserService;
import br.com.desafio01.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {
    private UserService userService;
    private RoleService roleService;
    public UsuarioController(UserService userService,RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/usuarios")
    public ResponseEntity<List<UserResponse>> listarUsuario(){
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

    @PostMapping("/usuarios")
    public ResponseEntity criarUsuario(@RequestBody CreateUserDto createUserDto){
        var role = roleService.findByTipoUsuario(createUserDto.role());
        User user = new User(createUserDto,role);
        userService.saveUser(user);
        return ResponseEntity.ok("Usuario criado com sucesso");
    }

}
