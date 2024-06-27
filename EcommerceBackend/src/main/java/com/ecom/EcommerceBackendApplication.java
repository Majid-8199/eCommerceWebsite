package com.ecom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ecom.entity.User;
import com.ecom.enums.Role;
import com.ecom.repository.UserRepository;

@SpringBootApplication
public class EcommerceBackendApplication implements CommandLineRunner{

	@Autowired
	UserRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount=repository.findByRole(Role.Admin);
		if(adminAccount==null) {
			User admin=new User();
			admin.setEmail("admin@gmail.com");
			admin.setFirstname("Admin");
			admin.setLastname("Admin");
			admin.setRole(Role.Admin);
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			repository.save(admin);
		}
	}

}
