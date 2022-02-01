package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.configuration.jwt.JwtProvider;
import com.softserve.betterlearningroom.dao.UserDAO;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.entity.UserPrincipal;
import com.softserve.betterlearningroom.entity.roles.Roles;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;
import com.softserve.betterlearningroom.mapper.UserMapper;
import com.softserve.betterlearningroom.payload.AuthRequest;
import com.softserve.betterlearningroom.payload.SaveUserRequest;
import com.softserve.betterlearningroom.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private JwtProvider jwtProvider;
    private UserMapper userMapper;
    private UserDAO userDao;
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(AuthRequest request) throws UsernameNotFoundException {
        User user = userDao.findByEmail(request.getLogin()).orElseThrow(() -> new BadCredentialsException(
                String.format("User with email - %s, not found", request.getLogin())));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(
                    String.format("Wrong password for user with email - %s", request.getLogin()));
        }
        return jwtProvider.generateToken(user.getEmail(), user.getId(), null);
    }
    
    @Override
    public String setRole(String userRole) {
        Roles role = null;
        switch(userRole.trim()) {
            case("student"): role = Roles.STUDENT;
            break;
            case("teacher"): role = Roles.TEACHER;
            break;
        }
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Generating token with role: " + role);
        return jwtProvider.generateToken(user.getUsername(), user.getId(), role);
    }

    @Override
    public UserDTO saveUser(SaveUserRequest request) throws UserAlreadyExistsException {
        if (userDao.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    String.format("User with email - %s already exists", request.getEmail()));
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        return userMapper.userToUserDTO(userDao.save(user));
    }

    @Override
    public UserDTO updateUser(SaveUserRequest request, Long id) throws UserAlreadyExistsException {
        User user = userDao.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with id - %d, not found.", id)));

        if (!user.getEmail().equals(request.getEmail()) && userDao.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    String.format("User with email - %s already exists", request.getEmail()));
        }
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(request.isEnabled());
        return userMapper.userToUserDTO(userDao.update(user));
    }
}