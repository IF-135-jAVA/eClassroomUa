package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.configuration.jwt.JwtProvider;
import com.softserve.betterlearningroom.dao.UserDAO;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.entity.request.AuthRequest;
import com.softserve.betterlearningroom.entity.request.SaveUserRequest;
import com.softserve.betterlearningroom.entity.roles.Roles;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;
import com.softserve.betterlearningroom.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultAuthService implements AuthService {

    private JwtProvider jwtProvider;
    private UserMapper userMapper;
    private UserDAO userDao;
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(AuthRequest request, String userRole) throws UsernameNotFoundException{
        User user = userDao.findByEmail(request.getLogin()).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with email - %s, not found", request.getLogin())));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(String.format("Wrong password for user with email - %s", request.getLogin()));
        }
        Roles role = Roles.valueOf(userRole.toUpperCase().trim());
        
        
        return jwtProvider.generateToken(user.getEmail(), role);
    }

    @Override
    public UserDTO saveUser(SaveUserRequest request) throws UserAlreadyExistsException {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        return userMapper.userToUserDTO(userDao.save(user));
    }

    @Override
    public UserDTO updateUser(SaveUserRequest request, int id) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with id - %d, not found.", id)));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(request.isEnabled());
        return userMapper.userToUserDTO(userDao.update(user));
    }
}