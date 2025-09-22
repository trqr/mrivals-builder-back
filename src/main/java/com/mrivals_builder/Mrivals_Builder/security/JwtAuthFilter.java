package com.mrivals_builder.Mrivals_Builder.security;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.mrivals_builder.Mrivals_Builder.entities.User;
import com.mrivals_builder.Mrivals_Builder.exceptions.BadRequestException;
import com.mrivals_builder.Mrivals_Builder.exceptions.NotFoundException;
import com.mrivals_builder.Mrivals_Builder.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            String email = jwtUtils.extractEmail(token);
            String role = jwtUtils.extractRole(token);

            List<GrantedAuthority> authorities =
                    List.of(new SimpleGrantedAuthority("ROLE_" + role));

            // Crée un objet d’authentification avec l’utilisateur et ses rôles
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(email, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // Poursuit le traitement de la requête
        filterChain.doFilter(request, response);
    }
}


