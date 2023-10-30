//package com.jwt.example.jwtExample3.service;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.stereotype.Service;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//
//
//@Service
//@RequiredArgsConstructor
//public class LogoutService implements LogoutHandler {
//
//	
//	
//	@Override
//	public void logout(
//			HttpServletRequest request,
//			HttpServletResponse response,
//			Authentication authentication) 
//	{
//		String requestHeader = request.getHeader("Authorization");
//        String token = null;
//        
//        if (requestHeader != null && requestHeader.startsWith("Bearer")) 
//        {
//        	return;
//        } 
//        token = requestHeader.substring(7);
//
//	}
//	
//
//}
