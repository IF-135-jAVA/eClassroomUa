package com.softserve.betterlearningroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.softserve.betterlearningroom.dao.UserDao;
import com.softserve.betterlearningroom.entity.CustomUserDetails;
import com.softserve.betterlearningroom.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Override
	public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException(String.format("User with email - %s, not found", email)));
		return CustomUserDetails.userToCustomUserDetails(user);
	}

}
