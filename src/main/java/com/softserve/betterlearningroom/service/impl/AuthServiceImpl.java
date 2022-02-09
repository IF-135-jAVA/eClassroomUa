package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.ConfirmationTokenDAO;
import com.softserve.betterlearningroom.configuration.jwt.JwtProvider;
import com.softserve.betterlearningroom.configuration.util.EmailSender;
import com.softserve.betterlearningroom.dao.UserDAO;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.ConfirmationToken;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.entity.UserPrincipal;
import com.softserve.betterlearningroom.entity.roles.Roles;
import com.softserve.betterlearningroom.exception.TokenExpiredException;
import com.softserve.betterlearningroom.exception.TokenNotFoundException;
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
    private EmailSender emailSender;
    
    private static final String CONFIRM_YOUR_EMAIL = "Confirm your email";
    private static final String CONFIRM_YOUR_EMAIL_DESCRIPTION = "Thank you for registering. Please click on the below link to activate your account:";
    private static final String RESET_PASSWORD = "Reset your password";
    private static final String RESET_PASSWORD_DESCRIPTION = "Please click on the below link to reset your password:";

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
        User savedUser = userDao.save(user);
        log.info(String.format("Saving user with id: %d", savedUser.getId()));

        sendEmailRequest(savedUser, CONFIRM_YOUR_EMAIL, CONFIRM_YOUR_EMAIL_DESCRIPTION);

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
    public UserDTO confirmUser(String code) throws TokenExpiredException, TokenNotFoundException {
        User user = extractUserFromConfirmationToken(code);
        user.setConfirmed(true);
        return userMapper.userToUserDTO(userDao.update(user));
    }
    
    @Override
    public UserDTO changePassword(String code, String password) throws TokenExpiredException, TokenNotFoundException {
        User user = extractUserFromConfirmationToken(code);
        user.setPassword(passwordEncoder.encode(password));
        return userMapper.userToUserDTO(userDao.update(user));
    }
    
    @Override
    public void resetPasswordRequest(String email) throws TokenExpiredException, TokenNotFoundException {
        User user = userDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email - %s, not found.", email)));
        sendEmailRequest(user, RESET_PASSWORD, RESET_PASSWORD_DESCRIPTION);
    }

    private void sendEmailRequest(User user, String title, String description) {
        ConfirmationToken token = ConfirmationToken.builder().code(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now()).expiresAt(LocalDateTime.now().plusMinutes(15)).user(user).build();
        tokenDao.save(token);
        log.info(String.format("Saving token for the user with id: %d", user.getId()));

        emailSender.sendEmail(user, token, title, description);
        log.info(String.format("Send email for the user with email: %s", user.getEmail()));
    }
    
    private User extractUserFromConfirmationToken(String code) throws TokenNotFoundException, TokenExpiredException {
        ConfirmationToken token = tokenDao.findTokenByCode(code)
                .orElseThrow(() -> new TokenNotFoundException(String.format("Token with code - %s, not found.", code)));
        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("Sorry, your token has expired.");
        }
        User user = userDao.findById(token.getUser().getId()).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with id - %d, not found.", token.getUser().getId())));
        return user;
    }

}