package com.softserve.betterlearningroom.dao.extractor;

import com.softserve.betterlearningroom.entity.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getLong("id"));
        comment.setText(rs.getString("text"));
        comment.setAuthorId(rs.getLong("authorId"));
        comment.setAnnouncementId(rs.getLong("announcementId"));
        comment.setUserAssignmentId(rs.getLong("userAssignmentId"));
        comment.setMaterialId(rs.getLong("materialId"));
        comment.setDate(rs.getDate("date").toLocalDate().atStartOfDay());
        comment.setEnabled(rs.getBoolean("enabled"));
        return comment;
    }
}


