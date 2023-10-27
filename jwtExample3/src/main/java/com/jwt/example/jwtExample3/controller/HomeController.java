package com.jwt.example.jwtExample3.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jwt.example.jwtExample3.model.Users;
import com.jwt.example.jwtExample3.service.UserService;

@CrossOrigin
@Configuration
@RestController
@RequestMapping("/home")
public class HomeController implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")  // Replace with the origin of your frontend app
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }	
    @Autowired
    private UserService userService;

    @RequestMapping("/go")
    public String home() {
    	System.out.println("Go");
        return "Hello World!";
    }
    
    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public List<Users> getUser() {
      System.out.println("Users");
      return this.userService.getUsers();
    }

    
    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal)
    {
    	return principal.getName();
    }
}
