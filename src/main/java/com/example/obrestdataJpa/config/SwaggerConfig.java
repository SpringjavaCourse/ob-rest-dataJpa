package com.example.obrestdataJpa.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collection;
import java.util.Collections;

/**
 * Configuración Swagger para la generación de la documentación de la API REST
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails(){

        return new ApiInfo("Spring Boot Book API REST",
                "Library API REST docs",
                "1.0",
                "http://www.google.com",
                new Contact("Duvan","http://www.google.com","duvancastro1026@gmail.com"),
                "MIT",
                "http://www.google.com",
                Collections.emptyList());
    }

}
