package com.jarvis.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // ✅ Specify allowed frontend URLs
        config.setAllowedOrigins(List.of(
                "https://suhailmfos.github.io/suhail-service",
                "http://localhost:3000"
        ));

        // ✅ Allow specific HTTP methods
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // ✅ Allow specific headers instead of "*"
        config.setAllowedHeaders(List.of(
                "Authorization",
                "Content-Type",
                "Accept",
                "Origin",
                "X-Requested-With"
        ));

        // ✅ Allow credentials (for cookies or tokens)
        config.setAllowCredentials(true);

        // ✅ Expose specific headers to frontend
        config.setExposedHeaders(List.of("Authorization"));

        // ✅ Register CORS configuration for all endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
}
