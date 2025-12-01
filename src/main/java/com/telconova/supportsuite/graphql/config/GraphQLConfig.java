package com.telconova.supportsuite.graphql.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración para GraphQL.
 * Extiende WebMvcConfigurer para habilitar CORS específicamente para el endpoint /graphql.
 * Esto es necesario cuando el frontend y el backend están en diferentes dominios (desarrollo típico).
 */
@Configuration
public class GraphQLConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configura CORS para el endpoint de GraphQL estándar de Spring Boot
        registry.addMapping("/graphql")
                // En producción, se recomienda cambiar "*" por el dominio específico del frontend
                .allowedOrigins("*")
                // GET y POST son necesarios para las operaciones de Query/Mutation
                .allowedMethods("GET", "POST", "OPTIONS")
                // Permite todos los encabezados necesarios para la autenticación y GraphQL
                .allowedHeaders("*");
    }

}