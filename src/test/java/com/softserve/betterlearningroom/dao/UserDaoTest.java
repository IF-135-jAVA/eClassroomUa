package com.softserve.betterlearningroom.dao;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.mock;
//
//import java.util.Optional;
//
//import javax.sql.DataSource;
//
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.ResultSetExtractor;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import com.softserve.betterlearningroom.dao.UserDao;
//import com.softserve.betterlearningroom.dao.impl.UserDaoImpl;
//import com.softserve.betterlearningroom.entity.User;
//import com.softserve.betterlearningroom.dao.extractor.UserRowMapper;
//
//public class UserDaoTest {
//
//	/*
//	 * @Mock public NamedParameterJdbcTemplate template; public UserRowMapper
//	 * userMapper;
//	 *
//	 * @InjectMocks public UserDaoImpl userDao;
//	 *
//	 * @BeforeEach public void init() { MockitoAnnotations.initMocks(this);
//	 * userMapper = new UserRowMapper(); userDao = new UserDaoImpl(template,
//	 * userMapper); }
//	 */
//
//
//	@SuppressWarnings("unchecked")
//	@Test
//	public void findByIdTest() {
//		NamedParameterJdbcTemplate template = mock(NamedParameterJdbcTemplate.class);
//		UserRowMapper userMapper = new UserRowMapper();
//		UserDao userDao = new UserDaoImpl(template, userMapper);
//		User user = new User(1, "Keanu", "Reeves", "1234", "Keanu@gmail.com", true);
//		Mockito.when(template.queryForObject(Mockito.anyString(),  Mockito.any(MapSqlParameterSource.class), Mockito.any(RowMapper.class))).thenReturn(user);
//		assertEquals(Optional.of(user), userDao.findById(1));
//		// assertEquals("Keanu@gmail.com", userDao.findById(1).get().getEmail());
//	}
//
//}
