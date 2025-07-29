package br.com.desafio01.services;

import br.com.desafio01.entities.Role;
import br.com.desafio01.entities.User;
import br.com.desafio01.repository.RoleRepository;
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

    public List<User> findAllUsers(){
        return userRepository.findAll();
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

    public List<User> findByRole(Optional<Role> role) {
        return userRepository.findByRole(role);
    }
}
