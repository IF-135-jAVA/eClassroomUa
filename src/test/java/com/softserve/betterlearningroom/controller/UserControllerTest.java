package com.softserve.betterlearningroom.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.softserve.betterlearningroom.controller.UserController;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	
	
	@Test
	public void whenUserIdIsProvided_thenReturnCorrectResponce() {
		UserDTO userDTO = new UserDTO(1, "Keanu", "Reeves", "Keanu@gmail.com", true);
		given(userService.findById(Mockito.anyInt())).willReturn(userDTO);
		ResponseEntity<UserDTO> userResponse = userController.findUserById(1);
		assertEquals(userDTO, userResponse.getBody());
		assertEquals(HttpStatus.OK, userResponse.getStatusCode());
	}
	
	@Test
	public void whenFindAllUSers_thenReturnCorrectResponce() {
		List<UserDTO> userList = new ArrayList<UserDTO>();
		userList.add(new UserDTO(1, "Keanu", "Reeves", "Keanu@gmail.com", true));
		userList.add(new UserDTO(2, "Yurii", "Kotsiuba", "jurok3x@gmail.com", true));
		userList.add(new UserDTO(3, "Bob", "Smith", "bob@gmail.com", true));
		userList.add(new UserDTO(4, "John", "Doe", "jdoe@gmail.com", true));
		given(userService.findAll()).willReturn(userList);
		ResponseEntity<List<UserDTO>> userResponse = userController.findAllUsers();
		assertEquals(userList, userResponse.getBody());
		assertEquals("Bob", userResponse.getBody().get(2).getFirstName());
		assertEquals(HttpStatus.OK, userResponse.getStatusCode());
	}
}
