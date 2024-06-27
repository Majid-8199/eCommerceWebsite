package com.ecom.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.dto.JwtAuthenticationRequest;
import com.ecom.dto.LoginRequest;
import com.ecom.dto.RefreshTokenRequest;
import com.ecom.dto.RegisterRequest;
import com.ecom.entity.Order;
import com.ecom.entity.User;
import com.ecom.enums.OrderStatus;
import com.ecom.enums.Role;
import com.ecom.repository.OrderRepository;
import com.ecom.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private OrderRepository orderRepository;  
	
	public User signUp(RegisterRequest signUpRequest) {
		User user=new User();
		user.setEmail(signUpRequest.getEmail());
		user.setFirstname(signUpRequest.getFirstname());
		user.setLastname(signUpRequest.getLastname());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.setRole(Role.User);
		userRepository.save(user);
		
		Order order=new Order();
		order.setAmount(0);
		order.setTotalAmount(0);
		order.setDiscount(0);
		order.setUser(user);
		order.setOrderStatus(OrderStatus.PENDING);
		orderRepository.save(order);
		
		return user;
	}
	
	public JwtAuthenticationRequest login(LoginRequest loginRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		var user=userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new IllegalArgumentException("Invalid Email or Password"));
		var jwt=jwtService.generateToken(user);
		var refreshToken=jwtService.generateRefreshToken(new HashMap<>(),user);
		JwtAuthenticationRequest jwtAuthResponse=new JwtAuthenticationRequest();
		jwtAuthResponse.setToken(jwt);
		jwtAuthResponse.setRefreshToken(refreshToken);
		jwtAuthResponse.setUser(user);
		return jwtAuthResponse;
	}
	
	public JwtAuthenticationRequest refreshToken(RefreshTokenRequest refreshTokenRequest) {
		String userEmail=jwtService.extractUsername(refreshTokenRequest.getToken());
		User user=userRepository.findByEmail(userEmail).orElseThrow();
		if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
			var jwt=jwtService.generateToken(user);
			JwtAuthenticationRequest jwtAuthResponse=new JwtAuthenticationRequest();
			jwtAuthResponse.setToken(jwt);
			jwtAuthResponse.setRefreshToken(refreshTokenRequest.getToken());
			jwtAuthResponse.setUser(user);
			return jwtAuthResponse;
		}else {
	        throw new IllegalArgumentException("Invalid or expired refresh token");
	    }
	}
}
