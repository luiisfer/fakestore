package com.prueba.vinestraprueba.config;


import com.prueba.vinestraprueba.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthService authService;

    public JwtAuthenticationFilter(AuthService authService) {
        this.authService = authService;
    }




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();


        if ("/auth/login".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }



        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Falta el token en la cabecera Authorization.");
            return;
        }

        String token = authHeader.substring(7);
        if (token != null && authService.isValidToken(token)) {

            String username = extractUsernameFromToken(token);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    AuthorityUtils.NO_AUTHORITIES
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {

            SecurityContextHolder.getContext().setAuthentication(null);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write("Token inválido o expirado.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String extractUsernameFromToken(String token) {
        String[] splitToken = token.split("\\.");
        String payload = splitToken[1];
        String decodedPayload = new String(Base64.getDecoder().decode(payload));


        JSONObject json = new JSONObject(decodedPayload);
        return json.getString("user");
    }






}