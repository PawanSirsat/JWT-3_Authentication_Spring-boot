package com.jwt.example.jwtExample3.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.example.jwtExample3.model.Users;
import com.jwt.example.jwtExample3.service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping("/go")
    public String home() {
    	System.out.println("Go");
        return "Hello World!";
    }
    
    @GetMapping("/users")
    public List<Users> getUser() {
    	System.out.println("Users");
        return this.userService.getUsers();
    }
    
    @RequestMapping("/current-user")
    public String getLoggedInUser(Principal principal)
    {
    	return principal.getName();
    }
}
