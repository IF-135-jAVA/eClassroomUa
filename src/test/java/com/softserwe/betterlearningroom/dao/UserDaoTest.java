package com.softserwe.betterlearningroom.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.softserve.betterlearningroom.dao.UserDao;
import com.softserve.betterlearningroom.dao.impl.UserDaoImpl;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.mapper.UserRowMapper;


public class UserDaoTest {

	@Autowired
	private DataSource dataSource = new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript("classpath:db/schema.sql")
			.addScript("classpath:db/test-data.sql")
			.build();
	
	@Test
	public void findByIdTest() {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		UserRowMapper userMapper = new UserRowMapper();
		UserDao userDao = new UserDaoImpl(template, userMapper);
		User user = new User(1, "Keanu", "Reeves", "1234", "Keanu@gmail.com", true);
		assertEquals(Optional.of(user), userDao.findById(1));
	}
	
}
