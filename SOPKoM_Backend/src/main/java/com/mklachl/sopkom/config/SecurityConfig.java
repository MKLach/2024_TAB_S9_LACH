package com.mklachl.sopkom.config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mklachl.sopkom.authentification.CustomFilter;
import com.mklachl.sopkom.authentification.SOPKOMAuthenticationSuccessHandler;
import com.mklachl.sopkom.security.CustomUserDetailsService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

    @Value("${frontend.url}")
    private String frontendURL;
    
    @Value("${frontend.port}")
    private String frontendPort;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public CustomFilter getCustomFilter() throws Exception {
        CustomFilter filter= new CustomFilter ("/user", "GET");
        filter.setAuthenticationFailureHandler((request, response, exception) -> {
           
        	System.out.println("-------------------------------e");
        	System.out.println(request.getParameter("url"));
        	System.out.println("-------------------------------e");
        	
        	System.out.println("forwarding e");
        	
        	
        	//RequestDispatcher dd=request.getRequestDispatcher("/login?url="+request.getParameter("url"));
        	
			//dd.forward(request, response);
			
        	response.sendRedirect("/login?url="+request.getParameter("url"));
        	//System.out.println("error");
        });
        return filter;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        		//.addFilterBefore(getCustomFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                		.requestMatchers("/logout").permitAll()
                		// TODO testing, later for delete
                		.requestMatchers("/api/**").permitAll()
                		.requestMatchers("/error").permitAll()
                   		.requestMatchers("/not_auth").permitAll()
                   		.requestMatchers("/actuator/health").permitAll()
                        .anyRequest().authenticated())
                		
                .formLogin(
                        form -> form
                        
                        .loginPage("/login")
                        .permitAll()
                        .loginProcessingUrl("/login")
                        .permitAll()
                        .successHandler(new SOPKOMAuthenticationSuccessHandler(frontendURL, frontendPort))
                        /*.failureHandler(new AuthenticationFailureHandler() {
							
							@Override
							public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
									AuthenticationException exception) throws IOException, ServletException {
								request.getHeaderNames().asIterator().forEachRemaining((name) -> {
									
									System.out.println(name + " : " + request.getHeader(name));
									
									
								});
								
							}
						})*/
                        //.defaultSuccessUrl(frontendURL, true)
                       
                ).logout(
                    logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/home")
                        .permitAll()
                )
                
            
                .cors(t -> t.configurationSource(corsConfiguration()))
                
                //.csrf(AbstractHttpConfigurer::disable)
                
                .csrf((csrf) -> csrf.disable())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfiguration(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(frontendURL, "http://192.168.100.109:3000", "http://localhost:3000", "http://127.1.1.0:3000"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
        return urlBasedCorsConfigurationSource;
    }
}