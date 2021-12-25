package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.configuration.jwt.JwtProvider;
import com.softserve.betterlearningroom.dao.UserDao;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.entity.request.AuthRequest;
import com.softserve.betterlearningroom.entity.request.SaveUserRequest;
import com.softserve.betterlearningroom.entity.roles.Roles;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;
import com.softserve.betterlearningroom.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultAuthService implements AuthService {

    private JwtProvider jwtProvider;
    private UserMapper userMapper;
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(AuthRequest request, String userRole) {
        User user = userDao.findByEmail(request.getLogin()).get();
        Roles roles = null;
        switch (userRole) {
            case ("student"):
                roles = Roles.STUDENT;
                break;
            case ("teacher"):
                roles = Roles.TEACHER;
                break;
        }
        return jwtProvider.generateToken(user.getEmail(), roles);
    }

    @Override
    public UserDTO saveUser(SaveUserRequest request) throws UserAlreadyExistsException {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        userDao.save(user);
        return userMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO updateUser(SaveUserRequest request, int id) {
        User user = userDao.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with id - %d, not found.", id))
        );
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(request.isEnabled());
        userDao.update(user);
        return userMapper.userToUserDTO(user);
    }

}

