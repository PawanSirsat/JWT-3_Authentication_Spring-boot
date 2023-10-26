package com.jwt.example.jwtExample3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jwt.example.jwtExample3.model.Users;

@Service
public class UserService {

	private List<Users> storeList = new ArrayList<>();
	
	 public UserService() {
		 storeList.add(new Users(UUID.randomUUID().toString(), "Pawan","pawan@gmail.com"));
		 storeList.add(new Users(UUID.randomUUID().toString(), "Atul","pawan@gmail.com"));
		 storeList.add(new Users(UUID.randomUUID().toString(), "Vaibhav","pawan@gmail.com"));
		 storeList.add(new Users(UUID.randomUUID().toString(), "Sahil","pawan@gmail.com"));

	}
	 
	 public List<Users> getUsers()
	 {
		 return this.storeList;
	 }
}
