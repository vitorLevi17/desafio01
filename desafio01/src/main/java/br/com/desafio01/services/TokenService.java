package br.com.desafio01.services;

import br.com.desafio01.entities.User;
import br.com.desafio01.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TokenService {

    private UserRepository userRepository;

    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return user;
    }
}
