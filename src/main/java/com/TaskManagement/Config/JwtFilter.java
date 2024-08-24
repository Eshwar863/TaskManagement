package com.TaskManagement.Config;

import com.TaskManagement.ServiceImp.Jwt.JwtService;
import com.TaskManagement.ServiceImp.Jwt.UserDtoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext; // <-- Correct import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    String jwtToken = null;
    String username = null;

    if(authHeader != null && authHeader.startsWith("Bearer ")) {
        jwtToken = authHeader.substring(7);
        username = jwtService.extractUserName(jwtToken);
    }
    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = applicationContext.getBean(UserDtoService.class).loadUserByUsername(username);
        if (jwtService.validateToken(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

        filterChain.doFilter(request, response);
    }



    }

