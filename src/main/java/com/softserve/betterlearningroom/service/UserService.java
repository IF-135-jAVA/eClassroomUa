package com.softserve.betterlearningroom.service;

import java.util.List;

import com.softserve.betterlearningroom.dto.UserDTO;

public interface UserService {
	
	UserDTO findById(int id);
	
	UserDTO findByEmail(String email);
	
	List<UserDTO> findAll();

}
