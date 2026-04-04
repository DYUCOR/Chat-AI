package com.chat.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable) // desativa csrf (ok pra teste)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login.html",
                                "/script.js",
                                "/chat.html",
                                "/style.css",
                                "/**/*.js",
                                "/**/*.css"
                        ).permitAll() // libera essas rotas
                        .anyRequest().authenticated() // resto precisa login
                )
                .formLogin(AbstractHttpConfigurer::disable); // desativa tela padrão

        return http.build();
    }
}
