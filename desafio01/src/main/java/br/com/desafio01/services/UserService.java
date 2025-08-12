package br.com.desafio01.services;

import br.com.desafio01.dto.CreateUserDto;
import br.com.desafio01.dto.UpdateUserDto;
import br.com.desafio01.dto.UserResponse;
import br.com.desafio01.entities.Role;
import br.com.desafio01.entities.User;
import br.com.desafio01.repository.UserRepository;
import br.com.desafio01.services.exceptions.ConflictException;
import br.com.desafio01.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<UserResponse> findAllUsers(){
        var usuarios = userRepository.findAll();
        var responseList = responseUserResponse(usuarios);
        return responseList;
    }
    public List<UserResponse> findAllUsersNome(String nome){
        var usuarios = userRepository.findAll();
        var lista = responseUserResponse(usuarios);
        var responseList = lista.stream().filter(user -> user.nome() != null && user.nome().toLowerCase().contains(nome.toLowerCase()));
        return responseList.toList();
    }
    public UserResponse findByIdUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getNome(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getEndereco(),
                user.getDtAtualização()
        );
        return userResponse;
    }
    public User saveUser(CreateUserDto createUserDto,Role role){
        if (userRepository.findByUsername(createUserDto.username()).isPresent()){
            throw new ConflictException("Username inválido");
        }
        if (userRepository.findByEmail(createUserDto.email()).isPresent()){
            throw new ConflictException("Email inválido");
        }
        User user = new User(createUserDto,role);
        return userRepository.save(user);
    }
    public User updateUser(UpdateUserDto updateUserDto,Role role){
        User user = userRepository.findById(updateUserDto.id())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        var usuario_por_email = userRepository.findByEmail(updateUserDto.email());
        if (usuario_por_email.isPresent() && usuario_por_email.get() != user){
            throw new ConflictException("Email inválido");
        }
        user.setNome(updateUserDto.nome());
        user.setEmail(updateUserDto.email());
        user.setEndereco(updateUserDto.endereco());
        user.setRole(role);
        return userRepository.save(user);
    }
    public void deleteUser(Long id){
        var usuario = userRepository.findById(id);
        if (usuario.isPresent()) {
            userRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Usuario não encontrado");
        }
    }
    public List<UserResponse> findByRole(Optional<Role> role, Long id) {
        if (id < 1 || id > 2){
            throw new ResourceNotFoundException("Selecione um Id válido");
        }
        var users = userRepository.findByRole(role);
        var response = responseUserResponse(users);
        return response;
    }
    private List<UserResponse> responseUserResponse(List<User> usuarios){
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
        return responseList;
    }
}
