package com.mklachl.sopkom.authentification;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SOPKOMAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {

	private String ip;
	private String port;
	
	public SOPKOMAuthenticationSuccessHandler(String ip,String port) {
		this.port = port;
		this.ip = ip;
	}

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
			System.out.println(request.getRemoteAddr());
			System.out.println(request.getLocalAddr());
		
			request.getHeaderNames().asIterator().forEachRemaining((name) -> {
				
				System.out.println(name + " : " + request.getHeader(name));
				
				
			});
			
			//System.out.println("-------------------------------");
	    	//System.out.println(request.getParameter("url"));
	    	//System.out.println("-------------------------------");
			
			//redirectStrategy.sendRedirect(request, response, request.getParameter("url"));
			
	    	//response.sendRedirect(request.getRemoteAddr() + ":3000");

        	//RequestDispatcher dd =request.getRequestDispatcher(request.getRemoteAddr()+":3000");
        	
			//dd.forward(request, response);
			
	    	
			//response.encodeRedirectURL("");
			//response.
			System.out.println(request.getLocalAddr());
			System.out.println(request.getRemoteAddr());
			
			if(request.getLocalAddr().equals("0:0:0:0:0:0:0:1")) {
				this.setDefaultTargetUrl("http://localhost:" + this.port);
			} else {
				//this.setDefaultTargetUrl("http://"+request.getLocalAddr()+":"+this.port);
				this.setDefaultTargetUrl(this.ip+":"+this.port);
			}
			
			this.setAlwaysUseDefaultTargetUrl(true);
			
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
