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
@PropertySource(value = "classpath:/comment_queries.properties")
public class CommentDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${findAll.Comments}")
    private String getAll;

    @Value("${findById.Comment}")
    private String getById;

    @Value("${save.Comment}")
    private String save;

    @Value("${update.Comment}")
    private String edit;

    @Value("${remove.Comment}")
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
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author_id", comment.getAuthor_id())
                .addValue("material_id", comment.getMaterial_id())
                .addValue("text", comment.getText())
                .addValue("date", comment.getDate());

        jdbcTemplate.update(save, parameterSource);
    }
    

    public void update(Comment updateComment) {
        BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(updateComment);
        jdbcTemplate.update(edit, parameterSource);
    }

    public void delete(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(remove, parameterSource);

    }
}
