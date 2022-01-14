package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.impl.UserDAOImpl;
import com.softserve.betterlearningroom.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = { TestDBConfiguration.class, UserDAOImpl.class })
class UserDaoTest {

    @Autowired
    UserDAO userDao;

    @Test
    void whenUserSaved_thenReturnCorrectUserCountAndFirstname() {
        User user = new User(5L, "Alice", "Cooper", "$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e",
                "acooper@gmail.com", true, "local", "111");
        userDao.save(user);
        assertEquals(5, userDao.findAll().size());
        assertEquals("Alice", userDao.findById(5L).get().getFirstName());
    }

    @Test
    void whenUserEmailIsProvided_thenReturnCorrectUser() {
        User user = new User(3L, "John", "Smith", "$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e",
                "jsmith@gmail.com", true, "local", "111");
        assertEquals(Optional.of(user), userDao.findByEmail("jsmith@gmail.com"));
    }
    
    @Test
    void whenUserIdIsProvided_thenReturnCorrectUser() {
        User user = new User(2L, "Yurii", "Kotsiuba", "$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e",
                "jurok3x@gmail.com", true, "local", "111");
        assertEquals(Optional.of(user), userDao.findById(2L));
    }

    @Test
    void whenUpdateUser_thenReturnCorrectUserEmail() {
        User user = new User(1L, "John", "Snow", "$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e",
                "snow@gmail.com", true, "local", "111");
        userDao.update(user);
        assertEquals("snow@gmail.com", userDao.findById(1L).get().getEmail());
    }

}
