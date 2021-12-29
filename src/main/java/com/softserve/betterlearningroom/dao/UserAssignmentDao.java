package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.UserAssignment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:/db/assignments/user_assignment_queries.properties")
public class UserAssignmentDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${create.user.assignment}")
    private String createQuery;

    @Value("${read.user.assignment.by.id}")
    private String readByIdQuery;

    @Value("${update.user.assignment}")
    private String updateQuery;

    @Value("${delete.user.assignment}")
    private String deleteQuery;

    @Value("${get.user.assignments.by.assignment}")
    private String getByAssignmentQuery;

    public UserAssignment create(UserAssignment userAssignment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userAssignment);
        jdbcTemplate.update(createQuery, parameterSource, keyHolder, new String[]{"id"});
        return readById(keyHolder.getKeyAs(Integer.class));
    }

    public UserAssignment readById(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        try {
            return jdbcTemplate.queryForObject(readByIdQuery, parameterSource, BeanPropertyRowMapper.newInstance(UserAssignment.class));
        } catch (EmptyResultDataAccessException e) {
            throw new DataRetrievalFailureException("UserAssignment with id - " + id + ", not found.");
        }
    }

    public UserAssignment update(UserAssignment userAssignment) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userAssignment);
        jdbcTemplate.update(updateQuery, parameterSource);
        return readById(userAssignment.getId());
    }

    public void delete(long id) {
        readById(id);
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(deleteQuery, parameterSource);
    }

    public List<UserAssignment> getByAssignment(long assignmentId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("assignmentId", assignmentId);
        return jdbcTemplate.query(getByAssignmentQuery, parameterSource, BeanPropertyRowMapper.newInstance(UserAssignment.class));
    }
}
