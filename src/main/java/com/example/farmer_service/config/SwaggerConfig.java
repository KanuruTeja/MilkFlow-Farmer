package com.example.farmer_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI milkFlowOpenAPI() {
        return new OpenAPI()
                .info( new Info()
                        .title("MilkFlow - Farmer Service API")
                        .description("APIs for managing milk supply by farmers")
                        .version("1.0.0"));
    }
}

