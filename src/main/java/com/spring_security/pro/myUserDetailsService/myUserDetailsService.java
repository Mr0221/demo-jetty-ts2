package com.spring_security.pro.myUserDetailsService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring_security.pro.entiy.Users;

@Service
public class myUserDetailsService implements  UserDetailsService{

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//dao sql by username;
		Users user = new Users();
		user.setUsername("tom");
		user.setPassword("123");
		user.setRole("ROLE_USER");
		return user;
	}

}
