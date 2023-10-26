package com.jwt.example.jwtExample3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.id.UUIDGenerationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.example.jwtExample3.model.Users;
import com.jwt.example.jwtExample3.repo.UserRepository;

@Service
public class UserService {

    @Autowired
	private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
	 
	 public List<Users> getUsers()
	 {
		 return userRepository.findAll();
	 }
	 
	 public Users createUser(Users user)
	 {
		 user.setUserId(UUID.randomUUID().toString());
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		 return userRepository.save(user);
	 }
}
