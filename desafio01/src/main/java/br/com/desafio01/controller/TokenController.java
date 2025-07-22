package br.com.desafio01.controller;

import br.com.desafio01.config.SecurityConfig;
import br.com.desafio01.dto.LoginDto;
import br.com.desafio01.dto.LoginResponse;
import br.com.desafio01.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;

    private final UserRepository userRepository; //TROCAR PARA SERVICE!!!!!!!!!!!!!!!!!!!!!!

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder jwtEncoder,UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository=userRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginDto loginDto){

        var user = userRepository.findByUsername(loginDto.username());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginDto,bCryptPasswordEncoder)){
            throw new BadCredentialsException("Usuario ou senha inválidos");
        }

        var now = Instant.now();
        long expiresIn = 600;

        var clains = JwtClaimsSet.builder()
                .issuer("my-back-end")
                .subject(user.get().getId().toString())
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(clains)).getTokenValue();
        return ResponseEntity.ok(new LoginResponse(jwtValue,expiresIn));
    }

}
