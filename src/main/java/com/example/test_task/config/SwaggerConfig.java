package com.example.test_task.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration

public class SwaggerConfig {


    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Test title")
                .description("asd")
                .contact(
                        new Contact()
                                .name("Telneki")
                                .email("skylight762@gmail.com")
                )
        );
    }


}


