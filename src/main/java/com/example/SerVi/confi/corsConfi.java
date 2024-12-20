package com.example.SerVi.confi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
public class corsConfi {

    @Bean
    public SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
        http
                .cors().and() // Enable CORS
                .csrf().disable() // Disable CSRF for simplicity (not recommended for production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**", "/authenticate", "/Company/sign-up", "/api/company/ad/{userId}",
                                "/api/company/ads/{userId}" ,"/Client/sign-up", "/ad/{adId}","/api/Client/ads", "/api/Client/search/{name}",
                                "/api/Client/book-service","/api/Client/ad/{adId}","/api/company/bookings/{companyId}",
                                "/api/company/booking/{bookingId}/{status}","/api/Client/my-bookings/{userId}","/api/Client/review"
                                ).permitAll() // Public endpoints
                        .anyRequest().authenticated() // Other endpoints require authentication
                );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource1() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Frontend URL
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
