package com.softserve.betterlearningroom.dao;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.extractor.UserRowMapper;
import com.softserve.betterlearningroom.dao.impl.UserDaoImpl;
import com.softserve.betterlearningroom.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDBConfiguration.class, UserDaoImpl.class})
@TestPropertySource(properties = { "classpath:/resourses/user_queries.properties" })
public class UserDaoTest {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDao userDao;
	
	@Test
	public void findByIdTest() {
		User user = new User(1, "Yurii", "Kotsiuba",
				"$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e",
				"jurok3x@gmail.com", true);
		assertEquals(Optional.of(user), userDao.findById(1));
	}

}
