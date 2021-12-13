package com.softserwe.betterlearningroom.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import javax.sql.DataSource;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.util.ReflectionTestUtils;

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
		// assertEquals("Keanu@gmail.com", userDao.findById(1).get().getEmail());
	}
	
}
