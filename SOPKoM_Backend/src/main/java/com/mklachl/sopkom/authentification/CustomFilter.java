package com.mklachl.sopkom.authentification;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomFilter extends UsernamePasswordAuthenticationFilter {

    public CustomFilter (String urlLogin, String method) {
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(urlLogin, method));
    }
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
      
    	request.getAttributeNames().asIterator().forEachRemaining((name) -> {
			
			System.out.println(name + " : " + request.getAttribute(name));
			
		});
    	
    	System.out.println("-------------------------------");
    	System.out.println(request.getParameter("url"));
    	System.out.println("-------------------------------");
    
        return super.attemptAuthentication(request, response);
    }
}