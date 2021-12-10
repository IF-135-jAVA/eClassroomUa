package com.softserve.betterlearningroom.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.softserve.betterlearningroom.configuration.jwt.JwtProvider;
import com.softserve.betterlearningroom.dao.UserDao;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.AuthRequest;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DefaultAuthService implements AuthService{
	
	private AuthenticationManager authenticationManager;
	private JwtProvider jwtProvider;
	private UserMapper userMapper;
	private UserDao userDao;

	@Override
	public String login(AuthRequest request) {
		User user = userDao.findByEmail(request.getLogin()).get();
		return jwtProvider.generateToken(user.getEmail());
	}

	@Override
	public UserDTO saveUser(User user) {
		userDao.save(user);
		return userMapper.userToUserDTO(user);
	}

	@Override
	public UserDTO updateUser(User user) {
		userDao.update(user);
		return userMapper.userToUserDTO(user);
	}

}
