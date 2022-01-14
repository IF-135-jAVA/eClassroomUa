package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.configuration.jwt.JwtProvider;
import com.softserve.betterlearningroom.dao.UserDAO;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.entity.roles.Roles;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;
import com.softserve.betterlearningroom.mapper.UserMapper;
import com.softserve.betterlearningroom.payload.AuthRequest;
import com.softserve.betterlearningroom.payload.SaveUserRequest;
import com.softserve.betterlearningroom.service.impl.AuthServiceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(value = { MockitoExtension.class })
class AuthServiceTest {
    
    @Mock
    private UserDAO userDao;
    
    @Mock
    private JwtProvider jwtProvider;
    
    private PasswordEncoder passwordEncoder;  
    private AuthServiceImpl authService;
    private UserMapper userMapper;
    
    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();
        passwordEncoder = new BCryptPasswordEncoder();
        authService = new AuthServiceImpl(jwtProvider, userMapper, userDao, passwordEncoder);
    }
    
    @Test
    void whenLogin_ThenReturnToken() {
        User user = getUser();
        AuthRequest request = new AuthRequest("jdoe@gmail.com", "q1234");
        given(userDao.findByEmail(anyString())).willReturn(Optional.of(user));
        given(jwtProvider.generateToken(anyString(), any(Roles.class))).willReturn("token");
        assertEquals("token", authService.login(request, "student"));
        verify(userDao).findByEmail(anyString());
        verify(jwtProvider).generateToken(anyString(), any(Roles.class));
    }
    
    @Test
    void whenUserNotFound_ThenThrowException() {
        AuthRequest request = new AuthRequest("jdoe@gmail.com", "q1234");
        given(userDao.findByEmail(anyString())).willReturn(Optional.empty());
        assertThrows(BadCredentialsException.class, () -> authService.login(request, "student"));
        verify(userDao).findByEmail(anyString());
    }
    
    @Test
    void whenPasswordIsWrong_ThenThrowException() {
        User user  = getUser();
        AuthRequest request =  new AuthRequest("jdoe@gmail.com", "wrong_password");
        given(userDao.findByEmail(anyString())).willReturn(Optional.of(user));
        assertThrows(BadCredentialsException.class, () -> authService.login(request, "student"));
        verify(userDao).findByEmail(anyString());
    }
    
    @Test
    void whenRegisterUser_ThenReturnRegisteredUser() throws UserAlreadyExistsException {
        User user  = getUser();
        user.setId(0L);
        given(userDao.save(any(User.class))).willReturn(user);
        given(userDao.findByEmail(anyString())).willReturn(Optional.empty());
        SaveUserRequest request = getRequest();
        assertEquals(request.getEmail(), authService.saveUser(request).getEmail());
        verify(userDao).findByEmail(anyString());
        verify(userDao).save(any(User.class));
    }
    
    @Test
    void whenUserAlreadyExists_ThenThrowException() throws UserAlreadyExistsException {
        User user  = getUser();
        given(userDao.findByEmail(anyString())).willReturn(Optional.of(user));
        SaveUserRequest request = getRequest();
        assertThrows(UserAlreadyExistsException.class, () -> authService.saveUser(request));
        verify(userDao).findByEmail(anyString());
    }
    
    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(userDao);
    }
    
    private User getUser() {
        User user = User.builder()
                                  .id(1L)
                                  .email("jdoe@gmail.com")
                                  .firstName("John")
                                  .lastName("Doe")
                                  .password(passwordEncoder.encode("q1234"))
                                  .enabled(true)
                                  .build();
        return user;
    }
    
    private SaveUserRequest getRequest() {
        SaveUserRequest request = SaveUserRequest.builder()
                                                           .email("jdoe@gmail.com")
                                                           .firstName("Bob")
                                                           .lastName("Doe")
                                                           .password(passwordEncoder.encode("q1234"))
                                                           .enabled(true)
                                                           .build();
        return request;
    }

}
