package com.softserve.betterlearningroom.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;

public interface UserService extends UserDetailsService{
	
	User findById(int id);
	
	List<User> findAll();
	
	User saveUser(User user)  throws UserAlreadyExistsException;
	
	User updateUser(User user, int id);
	
	User addClassroom(int id, Classroom classroom);
	
	List<Classroom> getClassrooms(int id);
	
	void disableUser(int id);
	
	void deleteDisabledUsers();

}
