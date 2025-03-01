package com.examly.springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI().info(new Info()
                                .title("Build Ease")
                                .version("1.0.0")
                                .description(
                                                "BuildEase is an e-commerce platform specializing in construction materials. It offers a wide range of products, including cement, steel, bricks, sand, and aggregates.The platform aims to provide high-quality materials at competitive prices, with a focus on timely delivery and cost savings. Users can easily post their requirements, receive quotations, make payments, and have materials delivered to their location.")
                                .contact(new Contact()
                                                .name("Demo API Support")
                                                .email("support@buildease.com")
                                                .url("#")))
                                .addSecurityItem(new SecurityRequirement()
                                                .addList("Bearer Authentication"))
                                .components(new Components()
                                                .addSecuritySchemes("Bearer Authentication", new SecurityScheme()
                                                                .type(SecurityScheme.Type.HTTP)
                                                                .bearerFormat("JWT")
                                                                .scheme("bearer")));
        }

}