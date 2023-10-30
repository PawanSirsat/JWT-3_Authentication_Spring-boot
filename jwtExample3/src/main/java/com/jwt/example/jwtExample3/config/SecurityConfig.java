package com.jwt.example.jwtExample3.config;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.jwt.example.jwtExample3.security.JwtAuthenticationEntryPoint;
import com.jwt.example.jwtExample3.security.JwtAuthenticationFilter;
import com.jwt.example.jwtExample3.service.CustomerUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Autowired
    private CustomerUserDetailService customerUserDetailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
	
    @Autowired
    private JwtAuthenticationFilter filter;
    
    @Autowired
    private LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeRequests().
                requestMatchers("/test").authenticated().requestMatchers("/auth/login").permitAll().requestMatchers("/auth/create-user").permitAll()
                .requestMatchers("/home/current-user").permitAll()
                .requestMatchers("/home/users").permitAll()
                .anyRequest()
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler(
        		(request, response, authentication) -> 
                 SecurityContextHolder.clearContext()
        )
        ;
        return http.build();
        
        
    }
    
    @Bean
    public LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            // Clear the session cookies
            request.getSession().invalidate();

            // Clear any other session data here
        };
    }
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
	DaoAuthenticationProvider Provider = new DaoAuthenticationProvider();
	Provider.setUserDetailsService(customerUserDetailService);
	Provider.setPasswordEncoder(passwordEncoder);
	
	return Provider;
}

    @Bean
    public AuthenticationManager authenticationManagerSecurityConfig(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

}
