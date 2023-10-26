package com.jwt.example.jwtExample3.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.example.jwtExample3.model.Users;

public interface UserRepository extends JpaRepository<Users, String> {
	
	public Optional<Users> findByEmail(String email);

}
