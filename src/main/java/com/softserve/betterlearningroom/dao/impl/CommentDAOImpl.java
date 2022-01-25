package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
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

@Repository
@PropertySource(value = "classpath:/db/comments/comment_queries.properties")
public class CommentDAOImpl implements CommentDAO {
    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Value("${findById.comment}")
    private String getByIdComments;
    
    @Value("${save.comment}")
    private String saveComments;
    
    @Value("${update.comment}")
    private String editComments;
    
    @Value("${remove.comment}")
    private String removeComments;
    
    @Value("${findById.materialComment}")
    private String getByIdMaterialComments;
    
    @Value("${findById.announcementComment}")
    private String getByIdAnnouncementComments;
    
    @Value("${findById.userAssignmentComment}")
    private String getByIdUserAssignmentComments;
    
    @Value("${findBy.authorId}")
    private String getByAuthorId;

    @Autowired
    public CommentDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Comment findById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        Comment result = DataAccessUtils.singleResult(namedParameterJdbcTemplate.query(getByIdComments,
                parameterSource, BeanPropertyRowMapper.newInstance(Comment.class)));
        if (result == null) {
            throw new DataRetrievalFailureException("Comment with id - " + id + ", not found.");
        }
        return result;
    }

    @Override
    public Comment save(Comment comment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(comment);
        namedParameterJdbcTemplate.update(saveComments, parameterSource, keyHolder, new String[]{"id"});
        return findById(keyHolder.getKey().longValue());
    }

    @Override
    public Comment update(Comment updateComment) {
        BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(updateComment);
        namedParameterJdbcTemplate.update(editComments, parameterSource);
        return findById(updateComment.getId());
    }

    @Override
    public void delete(Long id) {
        findById(id);
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(removeComments, parameterSource);
    }

    @Override
    public List<Comment> findByMaterialId(Long materialId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("materialCommentsId", materialId);
        return namedParameterJdbcTemplate.query(getByIdMaterialComments, parameterSource, BeanPropertyRowMapper.newInstance(Comment.class));
    }

    @Override
    public List<Comment> findByAnnouncementId(Long announcementId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("announcementCommentsId", announcementId);
        return namedParameterJdbcTemplate.query(getByIdAnnouncementComments, parameterSource, BeanPropertyRowMapper.newInstance(Comment.class));
    }

    @Override
    public List<Comment> findByUserAssignmentId(Long userAssignmentId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("userAssignmentCommentsId", userAssignmentId);
        return namedParameterJdbcTemplate.query(getByIdUserAssignmentComments, parameterSource, BeanPropertyRowMapper.newInstance(Comment.class));
    }

    @Override
    public List<Comment> findByAuthorId(Long authorId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("authorId", authorId);
        return namedParameterJdbcTemplate.query(getByAuthorId, parameterSource, BeanPropertyRowMapper.newInstance(Comment.class));
    }

}