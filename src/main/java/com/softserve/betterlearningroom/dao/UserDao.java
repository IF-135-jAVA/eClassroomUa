package com.softserve.betterlearningroom.dao;

import java.util.List;
import java.util.Optional;

import com.softserve.betterlearningroom.entity.User;

public interface UserDao {
	
	List<User> findAll();
	
	Optional<User> findById(int id);
	
	Optional<User> findByEmail(String email);
	
	void save(User user);
	
	void update(User user);

}
