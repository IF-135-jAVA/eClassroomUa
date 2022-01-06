package com.softserve.betterlearningroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.configuration.jwt.JwtProvider;
import com.softserve.betterlearningroom.dao.impl.UserDAOImpl;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.service.CustomUserDetailsService;
import com.softserve.betterlearningroom.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.json.JacksonJsonParser;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(value = { CustomUserDetailsService.class, UserDAOImpl.class, TestDBConfiguration.class, JwtProvider.class })
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private JacksonJsonParser jsonParser;
    private ObjectMapper mapper;

    @Test
    public void whenUserIdIsProvided_thenReturnCorrectResponce() throws Exception {
        mapper = new ObjectMapper();
        jsonParser = new JacksonJsonParser();
        UserDTO user = new UserDTO(2, "Yurii", "Kotsiuba", "kotsiuba@gmail.com", true);
        given(userService.findById(Mockito.anyInt())).willReturn(user);
        ResultActions result = mvc.perform(get("/api/users/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        assertEquals(mapper.writeValueAsString(user), resultString);
        verify(userService).findById(2);
    }
    
    @Test
    public void whenUserIdIsBad_thenReturnError() throws Exception {
        given(userService.findById(Mockito.anyInt())).willThrow(UsernameNotFoundException.class);
        mvc.perform(get("/api/users/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(userService).findById(0);
    }
    
    @Test
    public void whenGetAllUsers_thenReturnCorrectUserList() throws Exception {
        List<UserDTO> userList = new ArrayList<>();
        userList.add(new UserDTO(1, "John", "Doe", "jdoe@gmail.com", true));
        userList.add(new UserDTO(2, "Yurii", "Kotsiuba", "kotsiuba@gmail.com", true));
        given(userService.findAll()).willReturn(userList);
        ResultActions result = mvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        
        String resultString = result.andReturn().getResponse().getContentAsString();
        
        jsonParser.parseList(resultString).get(0).toString();
   
        verify(userService).findAll();
    }

}