package com.example.mezuntakip.security;


import com.example.mezuntakip.service.UserDetailsServiceImpl;
import com.example.mezuntakip.utils.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = extractToken(request);
        if(token != null && jwtUtils.validateToken(token)) {
            String login = jwtUtils.extractLogin(token);
            UserDetails user = userService.loadUserByUsername(login);
            setUpSpringAuthentication(user);
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);

        if(!(authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))) {
            return request.getHeader(HEADER).replace(PREFIX, "");
        }

        return null;
    }

    private void setUpSpringAuthentication(UserDetails user) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
