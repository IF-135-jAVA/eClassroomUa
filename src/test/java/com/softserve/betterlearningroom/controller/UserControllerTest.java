package com.softserve.betterlearningroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.configuration.jwt.JwtProvider;
import com.softserve.betterlearningroom.dao.impl.UserDAOImpl;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.service.UserService;
import com.softserve.betterlearningroom.service.impl.CustomUserDetailsService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(value = { CustomUserDetailsService.class, UserDAOImpl.class, TestDBConfiguration.class, JwtProvider.class })
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private ObjectMapper mapper;

    @Test
    void whenUserIdIsProvided_thenReturnCorrectResponce() throws Exception {
        mapper = new ObjectMapper();
        UserDTO user = new UserDTO(2L, "Yurii", "Kotsiuba", "kotsiuba@gmail.com", true);
        given(userService.findById(Mockito.anyLong())).willReturn(user);
        ResultActions result = mvc.perform(get("/api/users/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        assertEquals(mapper.writeValueAsString(user), resultString);
        verify(userService).findById(2L);
    }
    
    @Test
    void whenUserIdIsBad_thenReturnError() throws Exception {
        given(userService.findById(Mockito.anyLong())).willThrow(UsernameNotFoundException.class);
        mvc.perform(get("/api/users/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(userService).findById(0L);
    }
    
    @Test
    void whenGetAllUsers_thenReturnStatus200() throws Exception {
        List<UserDTO> userList = new ArrayList<>();
        userList.add(new UserDTO(1L, "John", "Doe", "jdoe@gmail.com", true));
        userList.add(new UserDTO(2L, "Yurii", "Kotsiuba", "kotsiuba@gmail.com", true));
        given(userService.findAll()).willReturn(userList);
        mvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)); 
        verify(userService).findAll();
    }
    
    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(userService);
    }

}
