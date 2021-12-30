package com.softserve.betterlearningroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.betterlearningroom.BeLeRoApplication;
import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.entity.request.AuthRequest;
import com.softserve.betterlearningroom.entity.request.SaveUserRequest;
import java.io.UnsupportedEncodingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BeLeRoApplication.class,
        TestDBConfiguration.class }, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mvc;
    private ObjectMapper mapper;
    private AuthRequest request;
    private SaveUserRequest testUser;
    
    @Before
    public void setUp() throws UnsupportedEncodingException, Exception {
        mapper = new ObjectMapper();
        
        request = new AuthRequest();
        request.setLogin("jurok3x@gmail.com");
        request.setPassword("yawinpassword");
        
        testUser = new SaveUserRequest();
        testUser.setEmail("divinity@gmail.com");
        testUser.setFirstName("John");
        testUser.setLastName("Cena");
        testUser.setPassword("secret_password");
        testUser.setEnabled(true);
    }

    @Test
    public void whenLogin_thenReturnToken() throws Exception {
        String jsonRequestBody = mapper.writeValueAsString(request);

        mvc.perform(post("/api/auth/login")
                .content(jsonRequestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept("text/plain;charset=UTF-8")
                .param("role", "teacher"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }
    
    @Test
    public void whenRegisterUser_thenReturnStatus201() throws Exception {
        String jsonRequestBody = mapper.writeValueAsString(testUser);

        mvc.perform(post("/api/auth/registration")
                .content(jsonRequestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)); //TODO: check if id is correct
    }
    
    @Test
    public void whenRegistrationPasswordIsBlank_thenReturnStatus400() throws Exception {
        testUser.setPassword("   ");
        String jsonRequestBody = mapper.writeValueAsString(testUser);

        mvc.perform(post("/api/auth/registration")
                .content(jsonRequestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }
    
    @Test
    public void whenRegistrationEmailNotUnique_thenReturnStatus400() throws Exception {
        testUser.setEmail("jurok3x@gmail.com");
        String jsonRequestBody = mapper.writeValueAsString(testUser);

        mvc.perform(post("/api/auth/registration")
                .content(jsonRequestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }
    
}
