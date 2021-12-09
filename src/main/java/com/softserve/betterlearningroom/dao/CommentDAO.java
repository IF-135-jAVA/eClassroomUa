package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@PropertySource(value = "classpath:/user_queries.properties")
public class CommentDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Value("SELECT id, author, text, date FROM Comment")
    private String getAll;

    @Value("SELECT id, author, text, date FROM Comment WHERE id=:id")
    private String getById;

    @Value("INSERT INTO Comment (author, text, date) VALUES (:author, :text, :date)")
    private String save;

    @Value( "UPDATE Comment SET author=:author, text=:text, date=:date")
    private String edit;

    @Value("DELETE FROM comment WHERE id=:id")
    private String remove;

    public List<Comment> readAll() {
        return jdbcTemplate.query(getAll, BeanPropertyRowMapper.newInstance(Comment.class));
    }


    public Comment readById(long id) {
       SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(getById, parameterSource,
                BeanPropertyRowMapper.newInstance(Comment.class));
    }


    public void create(Comment comment) {
       BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(comment);
        jdbcTemplate.update(save, parameterSource);
    }

    public void update( Comment updateComment) {
       BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(updateComment);
        jdbcTemplate.update(edit, parameterSource);
    }

    public void delete(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(remove, parameterSource);

    }
}
