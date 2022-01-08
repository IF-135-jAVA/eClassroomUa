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
        User user = new User(5, "Alice", "Cooper", "$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e",
                "acooper@gmail.com", true);
        userDao.save(user);
        assertEquals(5, userDao.findAll().size());
        assertEquals("Alice", userDao.findById(5).get().getFirstName());
    }

    @Test
    void whenUserEmailIsProvided_thenReturnCorrectUser() {
        User user = new User(3, "John", "Smith", "$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e",
                "jsmith@gmail.com", true);
        assertEquals(Optional.of(user), userDao.findByEmail("jsmith@gmail.com"));
    }
    
    @Test
    void whenUserIdIsProvided_thenReturnCorrectUser() {
        User user = new User(2, "Yurii", "Kotsiuba", "$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e",
                "jurok3x@gmail.com", true);
        assertEquals(Optional.of(user), userDao.findById(2));
    }

    @Test
    void whenUpdateUser_thenReturnCorrectUserEmail() {
        User user = new User(1, "John", "Snow", "$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e",
                "snow@gmail.com", true);
        userDao.update(user);
        assertEquals("snow@gmail.com", userDao.findById(1).get().getEmail());
    }

}
