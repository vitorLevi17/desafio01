package br.com.desafio01.controller;

import br.com.desafio01.dto.CreateUserDto;
import br.com.desafio01.dto.UpdateUserDto;
import br.com.desafio01.entities.User;
import br.com.desafio01.entities.Role;
import br.com.desafio01.services.RoleService;
import br.com.desafio01.services.UserService;
import br.com.desafio01.dto.UserResponse;
import jakarta.validation.Valid;
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
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping("/usuarios/nome/{nome}")
    public ResponseEntity<List<UserResponse>> listarUsuariosPorNome(@PathVariable String nome){
        var usuarios = userService.findAllUsersNome(nome);
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UserResponse> buscarUsuarioPorId(@PathVariable Long id){
        var user = userService.findByIdUser(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/usuarios/tipo/{id}")
    public ResponseEntity<List<UserResponse>> listarUsuariosTipo(@PathVariable Long id){
        Optional<Role> role = roleService.findById(id);
        List<UserResponse> users = userService.findByRole(role,id);
        return ResponseEntity.ok(users);
    }
    @PostMapping("/usuarios")
    public ResponseEntity criarUsuario(@Valid @RequestBody CreateUserDto createUserDto){
        var role = roleService.findByTipoUsuario(createUserDto.role());
        userService.saveUser(createUserDto,role);
        return ResponseEntity.ok("Usuario criado com sucesso");
    }
    @PutMapping("/usuarios")
    public ResponseEntity editarUsuario(@RequestBody UpdateUserDto updateUserDto){
        var role = roleService.findByTipoUsuario(updateUserDto.role());
        userService.updateUser(updateUserDto,role);
        return ResponseEntity.ok("O usuario " +updateUserDto.id()+ " foi editado com sucesso");
    }
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity deletarUsuario(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("O usuario: "+id+", foi deletado com sucesso");
    }
}