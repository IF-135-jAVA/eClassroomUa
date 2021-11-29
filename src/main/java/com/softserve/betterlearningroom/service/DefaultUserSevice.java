package com.softserve.betterlearningroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;
import com.softserve.betterlearningroom.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DefaultUserSevice implements UserService {

	private final UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) throws UserAlreadyExistsException {
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new UserAlreadyExistsException(String.format("User with email - %s, already exists", user.getEmail()));
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@Override
	public User updateUser(User updatedUser, int id) {
		User newUser = userRepository.findById(id)
				.map(user -> {
					user.setEmail(updatedUser.getEmail());
					user.setLastName(updatedUser.getLastName());
					user.setFirstName(updatedUser.getFirstName());
					user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
					user.setRole(updatedUser.getRole());
					return user;
				})
				.orElseGet(() -> updatedUser);
		return userRepository.save(newUser);
	}

	@Override
	public void disableUser(int id) {
		userRepository.deactivateUserById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException(String.format("User with email - %s, not found", email)));
		return user;
	}

	@Override
	public User findById(int id)  throws UsernameNotFoundException{
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User with id - %d, not found", id)));
		return user;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User addClassroom(int id, Classroom classroom) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User with id - %d, not found", id)));
		user.getClassrooms().add(classroom);
		return userRepository.save(user);
	}

	@Override
	public List<Classroom> getClassrooms(int id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User with id - %d, not found", id)));
		return user.getClassrooms();
	}

	@Override
	public void deleteDisabledUsers() {
		userRepository.deleteDeactivatedUsers();	
	}

}
