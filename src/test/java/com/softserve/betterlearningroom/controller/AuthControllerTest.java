package com.softserve.betterlearningroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.request.AuthRequest;
import com.softserve.betterlearningroom.entity.request.SaveUserRequest;
import com.softserve.betterlearningroom.service.AuthService;
import com.softserve.betterlearningroom.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AuthController.class, useDefaultFilters = false, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = AuthController.class) })
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;
    private ObjectMapper mapper;
    private AuthRequest authRequest;
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AuthService authService;

    @MockBean
    private UserService userService;

    @Test
    void whenLogin_thenReturnStatus200() throws Exception {
        mapper = new  ObjectMapper();
        UserDTO user = getUser();
        given(authService.login(any(AuthRequest.class), anyString())).willReturn("token");
        given(userService.findByEmail(anyString())).willReturn(user);
        authRequest = new AuthRequest("jdoe@gmail.com", "12345");
        mvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(authRequest))
                .param("role", "user"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(header().exists(HttpHeaders.AUTHORIZATION));
        verify(authService).login(any(AuthRequest.class), anyString());
    }

    @Test
    void whenRegistration_thenReturnStatus201() throws Exception {
        mapper = new  ObjectMapper();
        passwordEncoder = new BCryptPasswordEncoder();
        SaveUserRequest saveRequest = getRequest();
        UserDTO user = getUser();
        given(authService.saveUser(any(SaveUserRequest.class))).willReturn(user);
        mvc.perform(post("/api/auth/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(saveRequest)))
        .andExpect(status().isCreated())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(authService).saveUser(any(SaveUserRequest.class));
    }

    @Test
    void whenUpdateUser_thenReturnStatus200() throws Exception {
        mapper = new  ObjectMapper();
        passwordEncoder = new BCryptPasswordEncoder();
        SaveUserRequest saveRequest = getRequest();
        UserDTO user = getUser();
        given(authService.updateUser(any(SaveUserRequest.class), anyLong())).willReturn(user);
        mvc.perform(put("/api/auth/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(saveRequest)))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(authService).updateUser(any(SaveUserRequest.class), anyLong());
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(authService);
    }

    private UserDTO getUser() {
        UserDTO user = UserDTO.builder()
                                  .id(1L)
                                  .email("jdoe@gmail.com")
                                  .firstName("John")
                                  .lastName("Doe")
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
