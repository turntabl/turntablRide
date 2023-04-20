package com.ttrides.turntablRides.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
/*
Global security config to decide how security (authentication and authorization should be implemented
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${application.jwt.jwk-set-uri}")
    private String JWK_SET_URI;

    /*
    We are telling spring to make sure all requests require authentication
    We are also disabling csrf protection and cors
    We will not be maintaining sessions hence we will use a STATELESS creation policy
    We will be using an oauth2Resource server for jwt authentication. This server requires a bean of JwtDecoder
    to exist, and we create one using NimbusJwtDecoder and a user for Google's key certs
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .authorizeHttpRequests(authorize -> authorize.anyRequest().fullyAuthenticated())
                .csrf().disable()
                .cors().disable()
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer().jwt();

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(JWK_SET_URI).build();
    }
}
