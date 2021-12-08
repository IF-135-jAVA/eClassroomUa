package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Comment> readAll() {
        return jdbcTemplate.query("SELECT id, author, text, date FROM Comment",
                BeanPropertyRowMapper.newInstance(Comment.class));
    }


    public Comment readById(long id) {
        String sql = "SELECT id, author, text, date FROM Comment WHERE id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(sql, parameterSource,
                BeanPropertyRowMapper.newInstance(Comment.class));
    }


    public void create(Comment comment) {
        String sql = "INSERT INTO Comment (author, text, date) VALUES (:author, :text, :date)";
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(comment);
        jdbcTemplate.update(sql, parameterSource);
    }

    public void update( Comment updateComment) {
        String sql = "UPDATE Comment SET author=:author, text=:text, date=:date";
        BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(updateComment);
        jdbcTemplate.update(sql, parameterSource);
    }

    public void delete(long id) {
        String sql = "DELETE FROM comment WHERE id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, parameterSource);

    }
}
