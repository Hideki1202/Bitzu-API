package com.bitzu.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String SECRET = "eE9WJq83F9s0VWxD1Cz6hTwRL4xMZ9BqT2nH4MkFxYoVpJgNsLw5TrUw1HqAj7Xm";
    SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println(path.startsWith("/auth/"));

        // Permite o fluxo de requisições para /auth/ e /usuario/create
        if (path.startsWith("/auth/") || path.equals("/usuario/create")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replace("Bearer ", "");

            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)  // Usa a chave secreta segura gerada
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String email = claims.getSubject();

                // Cria um objeto de autenticação com a autoridade "ROLE_USER"
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(email, null, List.of());

                // Define a autenticação no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                // Se ocorrer erro na validação do token, limpa o contexto de segurança e retorna erro 401
                System.out.println("Erro ao processar o token: " + e.getMessage());
                SecurityContextHolder.clearContext();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        // Continua o filtro
        filterChain.doFilter(request, response);
    }
}
