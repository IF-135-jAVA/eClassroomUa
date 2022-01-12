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
    public Comment readByIdComment(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        Comment result = DataAccessUtils.singleResult(namedParameterJdbcTemplate.query(getByIdComments,
                parameterSource, BeanPropertyRowMapper.newInstance(Comment.class)));
        if (result == null) {
            throw new DataRetrievalFailureException("Comment with id - " + id + ", not found.");
        }
        return result;
    }

    @Override
    public Comment createComment(Comment comment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(comment);
        namedParameterJdbcTemplate.update(saveComments, parameterSource, keyHolder, new String[]{"id"});
        return readByIdComment(keyHolder.getKeyAs(Integer.class));
    }

    @Override
    public Comment updateComment(Comment updateComment) {
        BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(updateComment);
        namedParameterJdbcTemplate.update(editComments, parameterSource);
        return readByIdComment(updateComment.getId());
    }

    @Override
    public void deleteComment(long id) {
        readByIdComment(id);
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(removeComments, parameterSource);
    }

    @Override
    public List<Comment> readByIdMaterialComments(long materialCommentsId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("materialCommentsId", materialCommentsId);
        return namedParameterJdbcTemplate.query(getByIdMaterialComments, parameterSource, BeanPropertyRowMapper.newInstance(Comment.class));
    }

    @Override
    public List<Comment> readByIdAnnouncementComments(long announcementCommentsId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("announcementCommentsId", announcementCommentsId);
        return namedParameterJdbcTemplate.query(getByIdAnnouncementComments, parameterSource, BeanPropertyRowMapper.newInstance(Comment.class));
    }

    @Override
    public List<Comment> readByIdUserAssignmentComments(long userAssignmentCommentsId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("userAssignmentCommentsId", userAssignmentCommentsId);
        return namedParameterJdbcTemplate.query(getByIdUserAssignmentComments, parameterSource, BeanPropertyRowMapper.newInstance(Comment.class));
    }

    @Override
    public List<Comment> readByIdAuthorId(long authorId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("authorId", authorId);
        return namedParameterJdbcTemplate.query(getByAuthorId, parameterSource, BeanPropertyRowMapper.newInstance(Comment.class));
    }

}