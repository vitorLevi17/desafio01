package br.com.desafio01.controller;

import br.com.desafio01.dto.CreateUserDto;
import br.com.desafio01.dto.UpdateUserDto;
import br.com.desafio01.entities.Role;
import br.com.desafio01.entities.User;
import br.com.desafio01.services.RoleService;
import br.com.desafio01.services.UserService;
import br.com.desafio01.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

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
    @PutMapping("/usuarios")
    public ResponseEntity editarUsuario(@RequestBody UpdateUserDto updateUserDto){
        User user = userService.findById(updateUserDto.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setNome(updateUserDto.nome());
        user.setEmail(updateUserDto.email());
        user.setEndereco(updateUserDto.endereco());

        var role = roleService.findByTipoUsuario(updateUserDto.role());
        user.setRole(role);

        userService.saveUser(user);
        return ResponseEntity.ok("O usuario" +updateUserDto.id()+ "foi editado com sucesso");
    }
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity deletarUsuario(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("O usuario: "+id+", foi deletado com sucesso");
    }
}