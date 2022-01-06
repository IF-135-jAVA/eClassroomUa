package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.UserDAO;
import com.softserve.betterlearningroom.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@PropertySource(value = "classpath:/db/users/user_queries.properties")
@Slf4j
public class UserDAOImpl implements UserDAO {

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
            log.info(String.format("User with id - %d, not found.", id));
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
            log.info(String.format("User with email - %s, not found.", email));
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        
        KeyHolder keyHolder = new GeneratedKeyHolder();

        params.addValue("firstname", user.getFirstName()).addValue("lastname", user.getLastName())
                .addValue("email", user.getEmail()).addValue("password", user.getPassword())
                .addValue("enabled", user.isEnabled());

        template.update(save, params, keyHolder);
        int userId = 0;
        if(keyHolder.getKey() != null) {
            userId = keyHolder.getKey().intValue();
        }
        user.setId(userId);
        return user;
    }

    @Override
    public User update(User user) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("firstname", user.getFirstName()).addValue("lastname", user.getLastName())
                .addValue("email", user.getEmail()).addValue("password", user.getPassword())
                .addValue("enabled", user.isEnabled()).addValue("id", user.getId());

        template.update(update, params);
        
        return user;

    }

}
