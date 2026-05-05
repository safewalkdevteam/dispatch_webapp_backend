package com.safewalk.dispatch_webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class Config {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                .requestMatchers("/api/pings")
                .access(new WebExpressionAuthorizationManager(
                "hasIpAddress('10.0.0.0/8') or hasIpAddress('172.16.0.0/12') or hasIpAddress('192.168.0.0/16') or hasIpAddress('127.0.0.1/32')"
                ))
                .anyRequest().permitAll())
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
