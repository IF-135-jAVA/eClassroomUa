package com.softserve.betterlearningroom.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.softserve.betterlearningroom.BeLeRoApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BeLeRoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	private String token;
	
	JacksonJsonParser jsonParser;
	
	@Before
	public void setUp() throws Exception {
		jsonParser = new JacksonJsonParser();
		
		String request = "{\"login\":\"jurok3x@gmail.com\", \"password\":\"yawinpassword\"}";
		
		ResultActions result = mvc
				.perform(post("/api/auth/login?role=teacher")
				.content(request)
				.contentType(MediaType.APPLICATION_JSON)
	    		.accept("text/plain;charset=UTF-8"))
	        	.andExpect(status().isOk())
	        	.andExpect(content().contentType("text/plain;charset=UTF-8"));
		
		token = "Bearer " + result.andReturn().getResponse().getContentAsString();
	}
	
	
	@Test
	public void whenUserIdIsProvided_thenReturnCorrectResponce() throws Exception {
		ResultActions result = mvc.perform(get("/api/users/1")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token))
			.andExpect(status().isOk())
			.andExpect(content()
					.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		
		String resultString = result.andReturn().getResponse().getContentAsString();

		assertEquals("Comparing emails:", "jurok3x@gmail.com", jsonParser.parseMap(resultString).get("email").toString());
	}
	
	@Test
	public void whenUserIdIsBad_thenReturnError() throws Exception {
		mvc.perform(get("/api/users/10")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token))
			.andExpect(status().isInternalServerError())
			.andExpect(content()
					.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

}
