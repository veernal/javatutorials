package com.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// find user from database where user = :username
// userRepo.findByUsername(username);// username, password, roles

		if ("praveen".equals(username)) {
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	        authorities.add(new SimpleGrantedAuthority("ROLE_" + "DIRECTOR"));
//	        authorities.add(new SimpleGrantedAuthority("ROLE_" + "OWNER"));
			
			return new User(
					"praveen", 
//					"{noop}praveen@123", 
					"$2a$10$6jWKkPiqrdoDBYxX6xoF9uvGibq6XVMRBPpWLaXqNHfjVrXUYmNMa",
					authorities
				);
		} else if ("gopi".equals(username)) {
				Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		        authorities.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
//		        authorities.add(new SimpleGrantedAuthority("ROLE_" + "OWNER"));
				
				return new User(
						"gopi", 
//						"{noop}gopi@123", 
						"$2a$10$uNXwztCX4GEXBqagljs0oOpUX05fobmo24g7fai8e6ix8SU6OtFF6",
						authorities
					);
			} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
//
	}
}