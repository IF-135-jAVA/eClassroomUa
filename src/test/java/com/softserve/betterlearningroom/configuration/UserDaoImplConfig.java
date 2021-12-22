package com.softserve.betterlearningroom.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.softserve.betterlearningroom.dao.extractor.UserRowMapper;
import com.softserve.betterlearningroom.dao.impl.UserDaoImpl;

@Component
@PropertySource(value = "classpath:/user_queries.properties")
public class UserDaoImplConfig {
	
	@Autowired
	DataSource dataSource;
	
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
	
	@Bean
	public UserDaoImpl getUserDao() {
		
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		UserRowMapper userMapper = new UserRowMapper();
		UserDaoImpl userDaoImpl = new UserDaoImpl(template, userMapper);
		return userDaoImpl;
		
	}

}
