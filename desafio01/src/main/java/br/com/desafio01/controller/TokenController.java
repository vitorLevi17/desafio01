package br.com.desafio01.controller;

import br.com.desafio01.dto.LoginDto;
import br.com.desafio01.dto.LoginResponse;
import br.com.desafio01.dto.MudarSenhaDto;
import br.com.desafio01.entities.User;
import br.com.desafio01.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder jwtEncoder,UserService userService,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userService=userService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginDto loginDto){

        var user = userService.findByUsername(loginDto.username());

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
    @PutMapping("/mudar-senha")
    public ResponseEntity mudarSenha(@RequestBody MudarSenhaDto mudarSenhaDto){
        User user = userService.findByUsername(mudarSenhaDto.username()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (mudarSenhaDto.password().equals(mudarSenhaDto.confirm_password())){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(mudarSenhaDto.password()));
            userService.saveUser(user);
        }else {
            return ResponseEntity.ok("Usuario ou senha incorreto");
        }
        return ResponseEntity.ok("Senha alterada com sucesso");
    }
}