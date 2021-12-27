package com.softserve.betterlearningroom.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.softserve.betterlearningroom.dao.UserDao;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.mapper.UserMapper;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@Mock
	private UserDao userDao;
	
	private DefaultUserService userService;
	
	@Before
	public void setUp() {
		UserMapper userMapper = new UserMapper();
	    userService = new DefaultUserService(userDao, userMapper);
	}
	
	@Test
	public void whenUserIdIsProvided_thenReturnCorrectUser() {
		//Given
		User user = new User(1, "Keanu", "Reeves", "1234", "Keanu@gmail.com", true);
		given(userDao.findById(1)).willReturn(Optional.of(user));
		//when
		UserDTO userDTO = userService.findById(1);
		//then
		assertNotNull(userDTO);
		assertEquals(userDTO.getEmail() , "Keanu@gmail.com");
	}
	
	@Test
	public void whenUserEmailIsProvided_thenReturnCorrectUser() {
		User user = new User(1, "Keanu", "Reeves", "1234", "Keanu@gmail.com", true);
		given(userDao.findByEmail(Mockito.anyString())).willReturn(Optional.of(user));
		UserDTO userDTO = userService.findByEmail("Keanu@gmail.com");
		assertNotNull(userDTO);
		assertEquals(userDTO.getEmail() , "Keanu@gmail.com");
	}
	
	@Test
	public void whenUserIsNotFound_thenThrowException() {
		given(userDao.findById(Mockito.anyInt())).willReturn(Optional.ofNullable(null));
		assertThrows(UsernameNotFoundException.class, () -> userService.findById(1));
	}
	
	@Test
	public void whenGetAllUsers_thenReturnCorrectList() {
		List<User> userList = new ArrayList<User>();
		userList.add(new User(1, "Keanu", "Reeves", "1234", "Keanu@gmail.com", true));
		userList.add(new User(2, "Yurii", "Kotsiuba", "1234", "jurok3x@gmail.com", true));
		userList.add(new User(3, "Bob", "Smith", "1234", "bob@gmail.com", true));
		userList.add(new User(4, "John", "Doe", "1234", "jdoe@gmail.com", true));
		given(userDao.findAll()).willReturn(userList);
		List<UserDTO> actualUsers = userService.findAll();
		assertEquals(4, actualUsers.size());
		assertEquals("Bob", actualUsers.get(2).getFirstName());
	}

}
