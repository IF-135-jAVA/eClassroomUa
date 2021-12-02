package com.softserve.betterlearningroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Comment> readAll() {
        return jdbcTemplate.query("SELECT * FROM comment", new BeanPropertyRowMapper<>(Comment.class));
    }

    public Comment readById(int id) {
        return jdbcTemplate.query("SELECT * FROM comment WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Comment.class))
                .stream().findAny().orElse(null);
    }

    public void create(Comment comment) {
        jdbcTemplate.update("INSERT INTO comment VALUES(1, ?, ?,?)", comment.getAuthor(), comment.getDate(), comment.getText());

    }

    public void update(int id, Comment updateComment) {
        jdbcTemplate.update("UPDATE comment SET text=?, author=?, date=? WHERE id=?", updateComment.getText(),
                 updateComment.getAuthor(), updateComment.getDate(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM comment WHERE id=?", id);
    }
}
