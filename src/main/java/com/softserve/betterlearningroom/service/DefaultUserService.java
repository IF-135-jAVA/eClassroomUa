package com.softserve.betterlearningroom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.softserve.betterlearningroom.dao.UserDao;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService {

	private UserDao userRepository;
	private UserMapper userMapper;

	@Override
	public UserDTO findById(int id) throws UsernameNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User with id - %d, not found", id)));
		return userMapper.userToUserDTO(user);
	}
	
	@Override
	public UserDTO findByEmail(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User with email - %s, not found", email)));
		return userMapper.userToUserDTO(user);
	}
	
	@Override
	public UserDTO getClassroomOwner(int classroomId) throws UsernameNotFoundException {
		User user = userRepository.getClassroomOwner(classroomId)
				.orElseThrow(() -> new UsernameNotFoundException("Owner not found."));
		return userMapper.userToUserDTO(user);
	}

	@Override
	public List<UserDTO> findAll() {
		return userRepository.findAll().stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
	}
	
	@Override
	public List<UserDTO> getClassroomTeachers(int classroomId) {
		return userRepository.getClassroomTeachers(classroomId).stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
	}
	
	@Override
	public List<UserDTO> getClassroomStudents(int classroomId) {
		return userRepository.getClassroomStudents(classroomId).stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
	}


}
