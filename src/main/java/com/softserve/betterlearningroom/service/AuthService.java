package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.AuthRequest;
import com.softserve.betterlearningroom.entity.User;

public interface AuthService {
	
	String login(AuthRequest request);
	
	UserDTO saveUser(User user);
	
	UserDTO updateUser(User user);

}
