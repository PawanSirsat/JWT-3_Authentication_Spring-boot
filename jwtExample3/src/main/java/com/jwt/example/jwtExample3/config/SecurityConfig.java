package com.jwt.example.jwtExample3.config;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.example.jwtExample3.security.JwtAuthenticationEntryPoint;
import com.jwt.example.jwtExample3.security.JwtAuthenticationFilter;
import com.jwt.example.jwtExample3.service.CustomerUserDetailService;

@Configuration
public class SecurityConfig {


	@Autowired
    private CustomerUserDetailService customerUserDetailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeRequests().
                requestMatchers("/test").authenticated().requestMatchers("/auth/login").permitAll().requestMatchers("/auth/create-user").permitAll()
                .anyRequest()
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
        
        
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
