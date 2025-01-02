package com.ms1Desafio03.ms1Desafio03.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Rest API - Desafio 3, MS - Event, Ticket")
                                .description("API para gerenciamento de eventos e ingressos")
                                .version("V1")
                                .contact(new Contact().name("Christakis"))
                );
    }
}
