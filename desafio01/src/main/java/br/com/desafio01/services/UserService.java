package br.com.desafio01.services;

import br.com.desafio01.entities.User;
import br.com.desafio01.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return user;
    }
}
