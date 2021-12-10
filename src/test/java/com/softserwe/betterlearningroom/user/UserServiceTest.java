package com.softserwe.betterlearningroom.user;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//
//import java.util.Optional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//
//import com.softserve.betterlearningroom.dao.UserDao;
//import com.softserve.betterlearningroom.dto.UserDTO;
//import com.softserve.betterlearningroom.entity.User;
//import com.softserve.betterlearningroom.mapper.UserMapper;
//import com.softserve.betterlearningroom.service.DefaultUserService;
//import com.softserve.betterlearningroom.service.UserService;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserServiceTest {
//
//	@Test
//	public void whenUserIdIsProvided_thenReturnCorrectUser() {
//		UserDao userDao = mock(UserDao.class);
//		UserMapper mapper = new UserMapper();
//		UserService userService = new DefaultUserService(userDao, mapper);
//		User user = new User(1, "Keanu", "Reeves", "1234", "Keanu@gmail.com", true);
//		Mockito.when(userDao.findById(1)).thenReturn(Optional.of(user) );
//		UserDTO userDTO = userService.findById(1);
//		assertNotNull(userDTO);
//		assertEquals("Keanu@gmail.com", userDTO.getEmail());
//	}
//
//}
