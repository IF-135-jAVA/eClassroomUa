package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.UserDAO;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.mapper.UserMapper;
import com.softserve.betterlearningroom.service.impl.UserServiceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(value = { MockitoExtension.class })
class UserServiceTest {
	
	private static final boolean USER_ENABLED = true;

	private static final String USER_EMAIL = "Keanu@gmail.com";

	private static final String USER_PASSWORD = "1234";

	private static final String USER_LASTNAME = "Reeves";

	private static final String USER_FIRSTNAME = "Keanu";

	private static final Long USER_ID = 1L;
	
	private static final String USER_PROVIDER = "local";

	@Mock
	private UserDAO userDao;
	
	private UserServiceImpl userService;
	private UserMapper userMapper;
	
	@BeforeEach
	void setUp() {
		userMapper = new UserMapper();
	    userService = new UserServiceImpl(userDao, userMapper);
	}
	
	@Test
	void whenUserIdIsProvided_thenReturnCorrectUser() {
		User user = new User(USER_ID, USER_FIRSTNAME, USER_LASTNAME, USER_PASSWORD, USER_EMAIL, USER_ENABLED, USER_PROVIDER);
		given(userDao.findById(USER_ID)).willReturn(Optional.of(user));
		UserDTO userDTO = userService.findById(USER_ID);
		assertNotNull(userDTO);
		assertEquals(USER_EMAIL, userDTO.getEmail());
		verify(userDao).findById(USER_ID);
	}
	
	@Test
	void whenUserEmailIsProvided_thenReturnCorrectUser() {
		User user = new User(USER_ID, USER_FIRSTNAME, USER_LASTNAME, USER_PASSWORD, USER_EMAIL, USER_ENABLED, USER_PROVIDER);
		given(userDao.findByEmail(Mockito.anyString())).willReturn(Optional.of(user));
		UserDTO userDTO = userService.findByEmail(USER_EMAIL);
		assertNotNull(userDTO);
		assertEquals(USER_EMAIL, userDTO.getEmail());
		verify(userDao).findByEmail(USER_EMAIL);
	}
	
	@Test
	void whenUserIsNotFound_thenThrowException() {
		given(userDao.findById(Mockito.anyLong())).willReturn(Optional.ofNullable(null));
		assertThrows(UsernameNotFoundException.class, () -> userService.findById(USER_ID));
		verify(userDao).findById(USER_ID);
	}
	
	@Test
	void whenGetAllUsers_thenReturnCorrectList() {
		List<User> userList = new ArrayList<>();
		userList.add(new User(USER_ID, USER_FIRSTNAME, USER_LASTNAME, USER_PASSWORD, USER_EMAIL, USER_ENABLED, USER_PROVIDER));
		userList.add(new User(2L, "Yurii", "Kotsiuba", USER_PASSWORD, "jurok3x@gmail.com", USER_ENABLED, USER_PROVIDER));
		userList.add(new User(3l, "Bob", "Smith", USER_PASSWORD, "bob@gmail.com", USER_ENABLED, USER_PROVIDER));
		userList.add(new User(4l, "John", "Doe", USER_PASSWORD, "jdoe@gmail.com", USER_ENABLED, USER_PROVIDER));
		given(userDao.findAll()).willReturn(userList);
		List<UserDTO> actualUsers = userService.findAll();
		assertEquals(4, actualUsers.size());
		assertEquals("Bob", actualUsers.get(2).getFirstName());
		verify(userDao).findAll();
	}
	
	@AfterEach
    void tearDown() {
        verifyNoMoreInteractions(userDao);
    }

}
