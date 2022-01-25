package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.QuestionDAO;
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
@PropertySource("classpath:db/materials/questionQuery.properties")
public class QuestionDAOImpl implements QuestionDAO {

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
    public QuestionDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Question> findAllByMaterialId(Long materialId) {
        return jdbcTemplate.query(getAllQuery, new MapSqlParameterSource("materialid", materialId), BeanPropertyRowMapper.newInstance(Question.class));
    }

    @Override
    public List<Question> findAllByMaterial(Material material) {
        return findAllByMaterialId(material.getId());
    }

    @Override
    public int save(Question question, Long materialId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("materialid", materialId);
        param.addValue("question", question.getQuestion());
        return jdbcTemplate.update(addQuery, param);
    }

    @Override
    public int update(Question question) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("questionid", question.getId());
        param.addValue("question", question.getQuestion());
        return jdbcTemplate.update(updateQuery, param);
    }

    @Override
    public int delete(Long questionId) {
        return jdbcTemplate.update(removeQuery, new MapSqlParameterSource("questionid", questionId));
    }
}