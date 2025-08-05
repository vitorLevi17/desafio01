package br.com.desafio01.services;

import br.com.desafio01.dto.UserResponse;
import br.com.desafio01.entities.Role;
import br.com.desafio01.entities.User;
import br.com.desafio01.repository.UserRepository;
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
    public List<UserResponse> findAllUsersNome(String nome){
        var usuarios = userRepository.findAll();
        List<UserResponse> responseList = usuarios.stream()
                .filter(user -> user.getNome() != null && user.getNome().toLowerCase().contains(nome.toLowerCase()))
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
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    public Optional<User> findByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return user;
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    public List<UserResponse> findByRole(Optional<Role> role, Long id) {
        if (id < 1 || id > 2){ //ids das roles de cliente e dono
            return null;
        }
        var users = userRepository.findByRole(role);
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
        return response;
    }
}
