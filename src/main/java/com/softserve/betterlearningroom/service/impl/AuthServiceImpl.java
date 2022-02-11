package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.configuration.jwt.JwtProvider;
import com.softserve.betterlearningroom.configuration.util.EmailSender;
import com.softserve.betterlearningroom.dao.UserDAO;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.ConfirmationToken;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.entity.UserPrincipal;
import com.softserve.betterlearningroom.entity.roles.Roles;
import com.softserve.betterlearningroom.exception.TokenNotFoundException;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;
import com.softserve.betterlearningroom.mapper.UserMapper;
import com.softserve.betterlearningroom.payload.AuthRequest;
import com.softserve.betterlearningroom.payload.SaveUserRequest;
import com.softserve.betterlearningroom.service.AuthService;
import com.softserve.betterlearningroom.service.ConfirmationTokenService;
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
    private ConfirmationTokenService tokenService;
    private PasswordEncoder passwordEncoder;
    private EmailSender emailSender;
    
    private static final String CONFIRM_EMAIL_TITLE = "Confirm your email";
    private static final String CONFIRM_EMAIL_DESCRIPTION = "Thank you for registering. Please click on the below link to activate your account:";
    private static final String CONFIRM_EMAIL_URL = "http://localhost:8080/api/auth/confirm?code=";
    private static final String RESET_PASSWORD_TITLE = "Reset your password";
    private static final String RESET_PASSWORD_DESCRIPTION = "Please click on the below link to reset your password:";
    private static final String RESET_PASSWORD_URL = "http://localhost:8080/api/auth/change_password?code=";

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
        switch (userRole.trim()) {
        case ("student"):
            role = Roles.STUDENT;
            break;
        case ("teacher"):
            role = Roles.TEACHER;
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
        user.setProvider("local");
        User savedUser = userDao.save(user);
        log.info(String.format("Saving user with id: %d", savedUser.getId()));

        sendEmailRequest(savedUser, CONFIRM_EMAIL_URL, CONFIRM_EMAIL_TITLE, CONFIRM_EMAIL_DESCRIPTION);

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

    @Override
    public UserDTO confirmUser(String code) throws TokenNotFoundException {
        User user = extractUserFromConfirmationToken(code);
        user.setConfirmed(true);
        return userMapper.userToUserDTO(userDao.update(user));
    }
    
    @Override
    public UserDTO changePassword(String code, String password) throws TokenNotFoundException {
        User user = extractUserFromConfirmationToken(code);
        user.setPassword(passwordEncoder.encode(password));
        return userMapper.userToUserDTO(userDao.update(user));
    }
    
    @Override
    public void confirmUserRequest(String email) {
        User user = userDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email - %s, not found.", email)));
        sendEmailRequest(user, CONFIRM_EMAIL_URL, CONFIRM_EMAIL_TITLE, CONFIRM_EMAIL_DESCRIPTION);
    }
    
    @Override
    public void resetPasswordRequest(String email) {
        User user = userDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email - %s, not found.", email)));
        sendEmailRequest(user, RESET_PASSWORD_URL, RESET_PASSWORD_TITLE, RESET_PASSWORD_DESCRIPTION);
    }

    private void sendEmailRequest(User user, String url, String title, String description) {
        ConfirmationToken token = ConfirmationToken.builder().code(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now()).expiresAt(LocalDateTime.now().plusMinutes(15)).user(user).build();
        tokenService.save(token);
        log.info(String.format("Saving token for the user with id: %d", user.getId()));

        emailSender.sendEmail(user.getEmail(), user.getFirstName() + " " + user.getLastName(), url + token.getCode(), title, description);
        log.info(String.format("Send email for the user with email: %s", user.getEmail()));
    }
    
    private User extractUserFromConfirmationToken(String code) throws TokenNotFoundException {
        ConfirmationToken token = tokenService.findTokenByCode(code);
        if(token == null) {
            throw new TokenNotFoundException(String.format("Token with code - %s, not found.", code));
        }
        User user = userDao.findById(token.getUser().getId()).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with id - %d, not found.", token.getUser().getId())));
        tokenService.delete(code);
        return user;
    }

}