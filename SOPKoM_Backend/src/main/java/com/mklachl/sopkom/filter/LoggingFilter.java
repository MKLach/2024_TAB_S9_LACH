package com.mklachl.sopkom.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.printf("Handling incoming request: %s, %s\n", request.getRequestURI(), request.getMethod());
        request.getParameterNames().asIterator().forEachRemaining(System.out::println);
        try {
        	filterChain.doFilter(request, response);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        
    }
}