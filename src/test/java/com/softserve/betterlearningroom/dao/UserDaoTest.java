package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.impl.UserDAOImpl;
import com.softserve.betterlearningroom.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDBConfiguration.class, UserDAOImpl.class})
public class UserDaoTest {
	
	@Autowired
	UserDAO userDao;
	
	@Test
	public void whenUserIdIsProvided_thenReturnCorrectUser() {
		User user = new User(1, "Yurii", "Kotsiuba",
				"$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e",
				"jurok3x@gmail.com", true);
		assertEquals(Optional.of(user), userDao.findById(1));
	}
	
	@Test
	public void whenGetAllUsers_thenReturnCorrectUserCount() {
		assertEquals(27, userDao.findAll().size());
	}
	
	@Test
	public void whenUpdateUser_thenReturnCorrectUserEmail() {
		User user = new User(3, "John", "Snow",
				"$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e",
				"snow@gmail.com", true);
		userDao.update(user);
		assertEquals("snow@gmail.com", userDao.findById(3).get().getEmail());
	}

}
