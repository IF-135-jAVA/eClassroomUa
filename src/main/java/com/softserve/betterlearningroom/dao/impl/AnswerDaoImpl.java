package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.AnswerDao;
import com.softserve.betterlearningroom.entity.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.support.DataAccessUtils;
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
public class AnswerDaoImpl implements AnswerDao {

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

    @Override
    public Answer save(Answer answer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(answer);
        jdbcTemplate.update(createQuery, parameterSource, keyHolder, new String[]{"id"});
        return findById(keyHolder.getKeyAs(Long.class));
    }

    @Override
    public Answer findById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        Answer result = DataAccessUtils.singleResult(jdbcTemplate.query(readByIdQuery, parameterSource, BeanPropertyRowMapper.newInstance(Answer.class)));
        if (result == null) {
            throw new DataRetrievalFailureException("Answer with id - " + id + ", not found.");
        }
        return result;
    }

    @Override
    public Answer update(Answer answer) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(answer);
        jdbcTemplate.update(updateQuery, parameterSource);
        return findById(answer.getId());
    }

    @Override
    public void delete(Long id) {
        findById(id);
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(deleteQuery, parameterSource);
    }

    @Override
    public List<Answer> findByUserAssignmentId(Long userAssignmentId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("userAssignmentId", userAssignmentId);
        return jdbcTemplate.query(getByUserAssignmentQuery, parameterSource, BeanPropertyRowMapper.newInstance(Answer.class));
    }
}
