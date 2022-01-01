package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:/db/answers/answer_queries.properties")
public class AnswerDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${create.answer}")
    private String createQuery;

    @Value("${read.answer.by.id}")
    private String readByIdQuery;

    @Value("${update.answer}")
    private String updateQuery;

    @Value("${delete.answer}")
    private String deleteQuery;

    @Value("${get.answers.by.user.assignment}")
    private String getByUserAssignmentQuery;

    public Answer create(Answer answer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(answer);
        jdbcTemplate.update(createQuery, parameterSource, keyHolder, new String[]{"id"});
        return readById(keyHolder.getKeyAs(Integer.class));
    }

    public Answer readById(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(readByIdQuery, parameterSource, BeanPropertyRowMapper.newInstance(Answer.class));
    }

    public Answer update(Answer answer) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(answer);
        jdbcTemplate.update(updateQuery, parameterSource);
        return readById(answer.getId());
    }

    public void delete(long id) {
        readById(id);
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(deleteQuery, parameterSource);
    }

    public List<Answer> getByUserAssignment(long userAssignmentId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("userAssignmentId", userAssignmentId);
        return jdbcTemplate.query(getByUserAssignmentQuery, parameterSource, BeanPropertyRowMapper.newInstance(Answer.class));
    }
}
