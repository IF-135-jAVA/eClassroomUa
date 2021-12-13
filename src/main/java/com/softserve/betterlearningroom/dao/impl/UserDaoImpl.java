package com.softserve.betterlearningroom.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.softserve.betterlearningroom.dao.UserDao;
import com.softserve.betterlearningroom.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@PropertySource(value = "classpath:/user_queries.properties")
public class UserDaoImpl implements UserDao {
	
	private final NamedParameterJdbcTemplate template;
	private final RowMapper<User> rowMapper;
	
	@Value("${find.all}")
	private String findAllUsers;
	
	@Value("${find.by_id}")
	private String findById;
	
	@Value("${find.by_email}")
	private String findByEmail;
	
	@Value("${save}")
	private String save;
	
	@Value("${update}")
	private String update;
	
	@Override
	public List<User> findAll() {
		return template.query(findAllUsers, rowMapper);
	}

	@Override
	public Optional<User> findById(int id) {
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		User user = null;
		try {
			user = template.queryForObject(findById, param, BeanPropertyRowMapper.newInstance(User.class));
		} catch (DataAccessException ex) {
			ex.printStackTrace();
		}
		return Optional.ofNullable(user);
	}
	
	@Override
	public Optional<User> findByEmail(String email) {
		SqlParameterSource param = new MapSqlParameterSource("email", email);
		User user = null;
		try {
			user = template.queryForObject(findByEmail, param, rowMapper);
		} catch (DataAccessException ex) {
			ex.printStackTrace();
		}
		return Optional.ofNullable(user);
	}

	@Override
	public void save(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("firstname", user.getFirstName())
				.addValue("lastname", user.getLastName())
				.addValue("email", user.getEmail())
				.addValue("password", user.getPassword())
				.addValue("enabled", user.isEnabled());

		template.update(save, params);
	}

	@Override
	public void update(User user) {

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("firstname", user.getFirstName())
				.addValue("lastname", user.getLastName())
				.addValue("email", user.getEmail())
				.addValue("password", user.getPassword())
				.addValue("enabled", user.isEnabled())
				.addValue("id", user.getId());

		template.update(update, params);

	}
	

}
