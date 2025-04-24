package com.bitzu.demo.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF, caso necessário
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Garante que a sessão não será criada
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/usuario/create").permitAll() // Permite essas URLs sem autenticação
                        .anyRequest().authenticated() // Requer autenticação para qualquer outra requisição
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro JWT
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint((request, response, authException) -> {
                            // Personaliza a resposta quando o usuário não for autenticado
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não autorizado");
                        })
                ) // Configuração do AuthenticationEntryPoint
                .build();
    }


}
