package com.MotherSon.CRM.securit;

import java.io.IOException;
 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
 
//import com.MotherSon.CRM.service.UserService;
import com.MotherSon.CRM.utils.JwtUtil;
 
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
//import com.MotherSon.CRM.security.services;
 
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
 
    private final JwtUtil jwtUtil;
    private final com.MotherSon.CRM.security.services.UserService userService;
 
    public JwtAuthenticationFilter(JwtUtil jwtUtil, com.MotherSon.CRM.security.services.UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
 
        String token = extractJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtUtil.extractUsername(token) != null) {
            String username = jwtUtil.extractUsername(token);
 
            // Load user details to set security context
            UserDetails userDetails = userService.loadUserByUsername(username);
 
            if (jwtUtil.isTokenValid(token, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
 
    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }
}
 