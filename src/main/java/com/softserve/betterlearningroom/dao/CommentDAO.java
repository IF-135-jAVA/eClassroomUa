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

    @Value("${findById.Comment}")
    private String getByIdComments;

    @Value("${save.Comment}")
    private String saveComments;

    @Value("${update.Comment}")
    private String editComments;

    @Value("${remove.Comment}")
    private String removeComments;

    @Value("${findById.materialComment}")
    private String getByIdMaterialComments;

    @Value("${findById.announcementComment}")
    private String getByIdAnnouncementComments;

    @Value("${findById.userAssignmentComment}")
    private String getByIdUserAssignmentComments;

    public List<Comment> readByIdComments(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.query(getByIdComments, parameterSource, BeanPropertyRowMapper.newInstance(Comment.class));
    }

    public void createComments(Comment comment) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("authorId", comment.getAuthorId())
                .addValue("materialId", comment.getMaterialId())
                .addValue("text", comment.getText())
                .addValue("date", comment.getDate())
                .addValue("announcementId", comment.getAnnouncementId())
                .addValue("userAssignmentId", comment.getUserAssignmentId());

        jdbcTemplate.update(saveComments, parameterSource);
    }

    public void updateComments(Comment updateComment) {
        BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(updateComment);
        jdbcTemplate.update(editComments, parameterSource);
    }

    public void deleteComments(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(removeComments, parameterSource);
    }

    public List<Comment> readByIdMaterialComments(long materialCommentsId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("materialCommentsId", materialCommentsId);
        return jdbcTemplate.query(getByIdMaterialComments, parameterSource, BeanPropertyRowMapper.newInstance(Comment.class));
    }

    public List<Comment> readByIdAnnouncementComments(long announcementCommentsId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("announcementCommentsId", announcementCommentsId);
        return jdbcTemplate.query(getByIdAnnouncementComments, parameterSource, BeanPropertyRowMapper.newInstance(Comment.class));
    }

    public List<Comment> readByIdUserAssignmentComments(long userAssignmentCommentsId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("userAssignmentCommentsId", userAssignmentCommentsId);
        return jdbcTemplate.query(getByIdUserAssignmentComments, parameterSource, BeanPropertyRowMapper.newInstance(Comment.class));
    }

}