package br.com.desafio01.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI desafio(){
        return new OpenAPI().info(
                new Info().title("Desafio - API SPRING").description("Uma API para realizar o gerenciamento de usuários").version("v.0.1").license(new License().name("Apache2.0").url("github.com/vitorLevi17"))
        );
    }

}
