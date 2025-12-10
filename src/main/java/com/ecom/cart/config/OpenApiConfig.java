package com.ecom.cart.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ecomCartOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ecom Cart Service API")
                        .description("API documentation for the Ecom shopping cart microservice")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Ecom Developer Team")
                                .email("support@ecom.com")
                        )
                );
    }
}