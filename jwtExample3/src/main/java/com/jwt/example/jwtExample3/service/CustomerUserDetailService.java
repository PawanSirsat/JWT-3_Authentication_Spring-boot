package com.jwt.example.jwtExample3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.example.jwtExample3.model.Users;
import com.jwt.example.jwtExample3.repo.UserRepository;

@Service
public class CustomerUserDetailService implements UserDetailsService {

	@Autowired
	public UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Load Data From DataBase
		
		Users user = userRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("User Not Find !!"));
		
		return user;
	}
	
	

}
