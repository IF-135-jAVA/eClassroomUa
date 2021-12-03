package com.softserve.betterlearningroom.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.softserve.betterlearningroom.dao.UserDao;
import com.softserve.betterlearningroom.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	private NamedParameterJdbcTemplate template;
	
	@Value("${users.find.all}")
	private String findAllQuery;
	
	@Value("${users.find.by_id}")
	private String findByIdQuery;
	
	@Value("${users.find.by_email}")
	private String findByEmailQuery;
	
	@Value("${users.save}")
	private String saveQuery;
	
	@Value("${users.update}")
	private String updateQuery;
	
	@Value("${users.get_classroom_owner}")
	private String getOwnerQuery;
	
	@Value("${users.get_classroom_teachers}")
	private String getTeachersQuery;
	
	@Value("${users.get_classroom_students}")
	private String getStudentsQuery;
	
	private RowMapper<User> rowMapper = (rs, rowNum) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setFirstName(rs.getString("firstname"));
		user.setLastName(rs.getString("lastname"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setEnabled(rs.getBoolean("enabled"));
		return user;
	};

	public UserDaoImpl(NamedParameterJdbcTemplate template) {
		super();
		this.template = template;
	}
	
	@Override
	public List<User> findAll() {
		return template.query(findAllQuery, rowMapper);
	}

	@Override
	public Optional<User> findById(int id) {
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		User user = null;
		try {
			user = template.queryForObject(findByIdQuery, param, BeanPropertyRowMapper.newInstance(User.class));
		} catch (DataAccessException ex) {
			ex.printStackTrace();
		}
		return Optional.ofNullable(user);
	}
	
	@Override
	public Optional<User> getClassroomOwner(int classroomId) {
		SqlParameterSource param = new MapSqlParameterSource("id", classroomId);
		User user = null;
		try {
			user = template.queryForObject(getOwnerQuery, param, BeanPropertyRowMapper.newInstance(User.class));
		} catch (DataAccessException ex) {
			ex.printStackTrace();
		}
		return Optional.ofNullable(user);
	}
	
	@Override
	public List<User> getClassroomTeachers(int classroomId) {
		SqlParameterSource param = new MapSqlParameterSource("id", classroomId);
		return template.query(getTeachersQuery, param, rowMapper);
	}
	
	@Override
	public List<User> getClassroomStudents(int classroomId) {
		SqlParameterSource param = new MapSqlParameterSource("id", classroomId);
		System.out.print(getStudentsQuery + classroomId);
		return template.query(getStudentsQuery, param, rowMapper);
	}

	@Override
	public void save(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("firstname", user.getFirstName())
				.addValue("lastname", user.getLastName())
				.addValue("email", user.getEmail())
				.addValue("password", user.getPassword())
				.addValue("enabled", user.isEnabled());

		template.update(saveQuery, params);
	}

	@Override
	public void update(User user) {

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("firstname", user.getFirstName())
				.addValue("lastname", user.getLastName())
				.addValue("email", user.getEmail())
				.addValue("password", user.getPassword())
				.addValue("enabled", user.isEnabled());

		template.update(updateQuery, params);

	}

	@Override
	public Optional<User> findByEmail(String email) {
		SqlParameterSource param = new MapSqlParameterSource("email", email);
		User user = null;
		try {
			user = template.queryForObject(findByEmailQuery, param, BeanPropertyRowMapper.newInstance(User.class));
		} catch (DataAccessException ex) {
			ex.printStackTrace();
		}
		return Optional.ofNullable(user);
	}

}
