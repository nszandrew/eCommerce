package com.ecommerce.firstversion.configuration.webcofig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("eCommerce RESTful API with Java 21 and Spring Boot 3")
                        .version("v1")
                        .description("Compra e venda de roupas")
                        .termsOfService("https://github.com/nszandrew/ecommerce-backend")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/nszandrew/ecommerce-backend")));
    }
}
