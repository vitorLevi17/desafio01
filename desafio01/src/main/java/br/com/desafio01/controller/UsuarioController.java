package br.com.desafio01.controller;

import br.com.desafio01.dto.CreateUserDto;
import br.com.desafio01.dto.UpdateUserDto;
import br.com.desafio01.entities.User;
import br.com.desafio01.entities.Role;
import br.com.desafio01.services.RoleService;
import br.com.desafio01.services.UserService;
import br.com.desafio01.dto.UserResponse;
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
        User user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getNome(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getEndereco(),
                user.getDtAtualização()
        );
        return ResponseEntity.ok(userResponse);
    }
    @GetMapping("/usuarios/tipo/{id}")
    public ResponseEntity<List<UserResponse>> listarUsuarios(@PathVariable Long id){
        if (id < 1 || id > 2){ //ids das roles de cliente e dono
            return (ResponseEntity<List<UserResponse>>) ResponseEntity.notFound();
        }
        Optional<Role> role = roleService.findById(id);
        List<User> users = userService.findByRole(role);

        List<UserResponse> response = users.stream()
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

        return ResponseEntity.ok(response);
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