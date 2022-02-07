package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.ConfirmationTokenDAO;
import com.softserve.betterlearningroom.configuration.jwt.JwtProvider;
import com.softserve.betterlearningroom.dao.UserDAO;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.ConfirmationToken;
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

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private JwtProvider jwtProvider;
    private UserMapper userMapper;
    private UserDAO userDao;
    private ConfirmationTokenDAO tokenDao;
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
        user.setConfirmed(false);
        User savedUser = userDao.save(user);
        log.info(String.format("Saving user with id: %d", savedUser.getId()));
        
        ConfirmationToken token = ConfirmationToken.builder().code(UUID.randomUUID().toString()).createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15)).user(savedUser).build();
        tokenDao.save(token);
        log.info(String.format("Saving token for the user with id: %d", savedUser.getId()));
        
        return userMapper.userToUserDTO(savedUser);
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
    
    public UserDTO confirmUser(String code) {
        ConfirmationToken token =  tokenDao.findTokenByCode(code).orElseThrow();
        User user = userDao.findById(token.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with id - %d, not found.", token.getUser().getId())));
        user.setConfirmed(true);
        return userMapper.userToUserDTO(userDao.update(user));
    }
}