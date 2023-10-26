package com.jwt.example.jwtExample3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.jwt.example.jwtExample3.model.Users;

@Configuration
class AppConfig {
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails user1 = User.builder().username("pawan").password("pawan456").roles("ADMIN").build();
//		UserDetails user2 = User.builder().username("ankit").password("ankit123").roles("ADMIN").build();
//		
//		return new InMemoryUserDetailsManager(user1,user2);
//
//	}
	
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder().
                username("pawan")
                .password(passwordEncoder().encode("pawan456")).roles("ADMIN").
                build();
        UserDetails user2 = User.builder().
                username("ankit")
                .password(passwordEncoder().encode("ankit456")).roles("ADMIN").
                build();

        return new InMemoryUserDetailsManager(user1,user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}