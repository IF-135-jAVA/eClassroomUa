package com.softserve.betterlearningroom.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.softserve.betterlearningroom.BeLeRoApplication;
import com.softserve.betterlearningroom.dao.UserDao;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(
		  classes = BeLeRoApplication.class,
		  webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ContextConfiguration
public class UserControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@Autowired
	UserDao userDao;
	
	
	@Test
	public void whenUserIdIsProvided_thenReturnCorrectResponce() throws Exception {
		mvc.perform(get("/api/users")
			      .contentType(MediaType.APPLICATION_JSON));
	}
	
	/*
	 * @Test public void whenFindAllUSers_thenReturnCorrectResponce() {
	 * List<UserDTO> userList = new ArrayList<UserDTO>(); userList.add(new
	 * UserDTO(1, "Keanu", "Reeves", "Keanu@gmail.com", true)); userList.add(new
	 * UserDTO(2, "Yurii", "Kotsiuba", "jurok3x@gmail.com", true)); userList.add(new
	 * UserDTO(3, "Bob", "Smith", "bob@gmail.com", true)); userList.add(new
	 * UserDTO(4, "John", "Doe", "jdoe@gmail.com", true));
	 * given(userService.findAll()).willReturn(userList);
	 * ResponseEntity<List<UserDTO>> userResponse = userController.findAllUsers();
	 * assertEquals(userList, userResponse.getBody()); assertEquals("Bob",
	 * userResponse.getBody().get(2).getFirstName()); assertEquals(HttpStatus.OK,
	 * userResponse.getStatusCode()); }
	 */

}
