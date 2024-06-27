package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ecom.dto.RefreshTokenRequest;
import com.ecom.dto.JwtAuthenticationRequest;
import com.ecom.dto.LoginRequest;
import com.ecom.dto.RegisterRequest;
import com.ecom.entity.User;
import com.ecom.service.AuthenticationService;

@RestController
@RequestMapping("/all")
@CrossOrigin("http://localhost:4200")
public class AuthController {

	@Autowired
	AuthenticationService authenticationService;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
		return ResponseEntity.ok(authenticationService.signUp(registerRequest));
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationRequest> login(@RequestBody LoginRequest loginRequest) throws IllegalAccessException{
		return ResponseEntity.ok(authenticationService.login(loginRequest));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<JwtAuthenticationRequest> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
		return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
	}
}
