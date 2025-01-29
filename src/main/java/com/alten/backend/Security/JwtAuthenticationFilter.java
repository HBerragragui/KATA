package com.alten.backend.Security;

import com.alten.backend.Exceptions.NotAuthorizedException;
import com.alten.backend.Repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtConfig jwtConfig;

    private final UserDetailsService userDetailsService;
    public JwtAuthenticationFilter(JwtConfig jwtConfig, UserDetailsService userDetailsService) {
        this.jwtConfig = jwtConfig;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null ) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getRequestURI().contains("/product") && (request.getMethod().equals("POST") || request.getMethod().equals("PATCH") || request.getMethod().equals("PUT")) && jwtConfig.getUserEmail(authHeader).equals("niama@outlook.fr"))
        {
            filterChain.doFilter(request, response);
            throw new NotAuthorizedException("User is not allowed to perform this action");
        }

        if (jwtConfig.validateToken(authHeader)) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtConfig.getUserEmail(authHeader));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}