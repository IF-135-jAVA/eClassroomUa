package com.softserve.betterlearningroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.betterlearningroom.BeLeRoApplication;
import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.entity.request.AuthRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BeLeRoApplication.class, TestDBConfiguration.class},  webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    private String token;
    private JacksonJsonParser jsonParser;
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        jsonParser = new JacksonJsonParser();
        mapper = new ObjectMapper();
        
        AuthRequest request = new AuthRequest();
        request.setLogin("jurok3x@gmail.com");
        request.setPassword("yawinpassword");
        
        String jsonRequestBody = mapper.writeValueAsString(request);

        token = "Bearer " + mvc
                .perform(post("/api/auth/login?role=teacher")
                        .content(jsonRequestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept("text/plain;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void whenUserIdIsProvided_thenReturnCorrectResponce() throws Exception {
        ResultActions result = mvc
                .perform(get("/api/users/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        assertEquals("Comparing emails:", "jurok3x@gmail.com",
                jsonParser.parseMap(resultString).get("email").toString());
    }

    @Test
    public void whenUserIdIsBad_thenReturnError() throws Exception {
        mvc.perform(get("/api/users/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
