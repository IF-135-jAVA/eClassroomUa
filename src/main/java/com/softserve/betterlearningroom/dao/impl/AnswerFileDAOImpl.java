package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.AnswerFileDAO;
import com.softserve.betterlearningroom.entity.AnswerFile;
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
import java.util.Objects;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:/db/answers/answer_file_queries.properties")
public class AnswerFileDAOImpl implements AnswerFileDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${create.answer.file}")
    private String createQuery;

    @Value("${read.answer.file.by.id}")
    private String readByIdQuery;

    @Value("${get.answer.files.by.user.assignment}")
    private String getByUserAssignmentQuery;

    @Override
    public AnswerFile save(AnswerFile answerFile) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(answerFile);
        jdbcTemplate.update(createQuery, parameterSource, keyHolder, new String[]{"id"});
        return findById(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    public AnswerFile findById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        AnswerFile result = DataAccessUtils.singleResult(jdbcTemplate.query(readByIdQuery, parameterSource, BeanPropertyRowMapper.newInstance(AnswerFile.class)));
        if (result == null) {
            throw new DataRetrievalFailureException("AnswerFile with id - " + id + ", not found.");
        }
        return result;
    }

    @Override
    public List<AnswerFile> findByUserAssignmentId(Long userAssignmentId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("userAssignmentId", userAssignmentId);
        return jdbcTemplate.query(getByUserAssignmentQuery, parameterSource, BeanPropertyRowMapper.newInstance(AnswerFile.class));
    }
}
