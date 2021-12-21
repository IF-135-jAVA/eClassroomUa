package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.entity.UserAssignment;
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

    @Value("${findAll.materialComments}")
    private String getAllMaterialComments;

    @Value("${findById.materialComment}")
    private String getByIdMaterialComments;

    @Value("${save.materialComment}")
    private String saveMaterialComments;

    @Value("${update.materialComment}")
    private String editMaterialComments;

    @Value("${remove.materialComment}")
    private String removeMaterialComments;


    public List<Comment> readAllMaterialComments() {
        return jdbcTemplate.query(getAllMaterialComments, BeanPropertyRowMapper.newInstance(Comment.class));
    }


    public Comment readByIdMaterialComments(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(getByIdMaterialComments, parameterSource,
                BeanPropertyRowMapper.newInstance(Comment.class));
    }


    public void createMaterialComments(Comment comment) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author_id", comment.getAuthor_id())
                .addValue("material_id", comment.getMaterial_id())
                .addValue("text", comment.getText())
                .addValue("date", comment.getDate());

        jdbcTemplate.update(saveMaterialComments, parameterSource);
    }


    public void updateMaterialComments(Comment updateComment) {
        BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(updateComment);
        jdbcTemplate.update(editMaterialComments, parameterSource);
    }

    public void deleteMaterialComments(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(removeMaterialComments, parameterSource);

    }

    @Value("${findAll.announcementComments}")
    private String getAllAnnouncementComments;

    @Value("${findById.announcementComment}")
    private String getByIdAnnouncementComments;

    @Value("${save.announcementComment}")
    private String saveAnnouncementComments;

    @Value("${update.announcementComment}")
    private String editAnnouncementComments;

    @Value("${remove.announcementComment}")
    private String removeAnnouncementComments;



    public List<Comment> readAllAnnouncementComments() {
        return jdbcTemplate.query(getAllAnnouncementComments, BeanPropertyRowMapper.newInstance(Comment.class));
    }


    public Comment readByIdAnnouncementComments(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(getByIdAnnouncementComments, parameterSource,
                BeanPropertyRowMapper.newInstance(Comment.class));
    }


    public void createAnnouncementComments(Comment comment) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author_id", comment.getAuthor_id())
                .addValue("announcement_id", comment.getAnnouncement_id())
                .addValue("text", comment.getText())
                .addValue("date", comment.getDate());

        jdbcTemplate.update(saveAnnouncementComments, parameterSource);
    }


    public void updateAnnouncementComments(Comment updateComment) {
        BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(updateComment);
        jdbcTemplate.update(editAnnouncementComments, parameterSource);
    }

    public void deleteAnnouncementComments(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(removeAnnouncementComments, parameterSource);

    }

}