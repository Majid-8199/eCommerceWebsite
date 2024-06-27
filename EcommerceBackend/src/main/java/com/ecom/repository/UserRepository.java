package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.User;
import com.ecom.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	public Optional<User> findByEmail(String email);
	public User findByRole(Role role);
	
}
