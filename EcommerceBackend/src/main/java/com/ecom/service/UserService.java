package com.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
			}
		};
		
	}
}
