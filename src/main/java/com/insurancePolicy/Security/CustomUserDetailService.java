package com.insurancePolicy.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.insurancePolicy.Entity.User;
import com.insurancePolicy.Exception.ResourceNotFoundException;
import com.insurancePolicy.Repository.UserRepo;
@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user by userName from database
		User user = this.repo.findByUserName(username).orElseThrow(()->new ResourceNotFoundException("User","UserName : "+username,0));
		return user;
	}

}
