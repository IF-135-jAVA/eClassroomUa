package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:questionQuery.properties")
public class QuestionDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${get.all.questions}")
    private String getAllQuery;

    @Value("${add.new.question}")
    private String addQuery;

    @Value("${update.question}")
    private String updateQuery;

    @Value("${remove.question}")
    private String removeQuery;

    @Autowired
    public QuestionDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Question> getAllQuestions(Long materialId) {
        return jdbcTemplate.query(getAllQuery, new MapSqlParameterSource("materialid", materialId), BeanPropertyRowMapper.newInstance(Question.class));
    }

    public List<Question> getAllQuestions(Material material) {
        return getAllQuestions(material.getId());
    }

    public int addQuestion(Question question, Long materialId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("materialid", materialId);
        param.addValue("question", question.getQuestion());
        return jdbcTemplate.update(addQuery, param);
    }

    public int addQuestion(Question question, Material material) {
        return addQuestion(question, material.getId());
    }

    public int updateQuestion(Question question) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("questionid", question.getId());
        param.addValue("question", question.getQuestion());
        return jdbcTemplate.update(updateQuery, param);
    }

    public int removeQuestion(Long questionId) {
        return jdbcTemplate.update(removeQuery, new MapSqlParameterSource("questionid", questionId));
    }

    public int removeQuestion(Question question) {
        return removeQuestion(question.getId());
    }

}