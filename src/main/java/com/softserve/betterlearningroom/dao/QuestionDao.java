package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.mapper.QuestionRowMapper;
import com.softserve.betterlearningroom.model.Material;
import com.softserve.betterlearningroom.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class QuestionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Question> getAllQuestions(Long materialId) {
        return jdbcTemplate.query("SELECT * FROM questions where materialid=?", new Object[] { materialId }, new QuestionRowMapper());
    }

    public List<Question> getAllQuestions(Material material) {
        return jdbcTemplate.query("SELECT * FROM questions where materialid=?", new Object[] { material.getId() }, new QuestionRowMapper());
    }

    public boolean addQuestion(Question question, Material material) {
        return jdbcTemplate.update("INSERT INTO questions (question, answer, materialid) VALUES (?, ?, ?)", question.getQuestion(), question.getAnswer(), material.getId()) == 1;
    }

    public boolean addQuestion(Question question, Long materialId) {
        return jdbcTemplate.update("INSERT INTO questions (question, answer, materialid) VALUES (?, ?, ?)", question.getQuestion(), question.getAnswer(), materialId) == 1;
    }

    public boolean updateQuestion(Question question) {
        return jdbcTemplate.update("UPDATE questions SET question=?, answer=? WHERE questionid=?", question.getQuestion(), question.getAnswer(), question.getId()) == 1;
    }

    public boolean removeQuestion(Long questionId) {
        return jdbcTemplate.update("DELETE FROM questions WHERE questionId=?", questionId) == 1;
    }

    public boolean removeQuestion(Question question) {
        return jdbcTemplate.update("DELETE FROM questions WHERE questionId=?", question.getId()) == 1;
    }

}