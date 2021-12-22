package com.softserve.betterlearningroom.dao.extractor;

import com.softserve.betterlearningroom.entity.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getLong("id"));
        comment.setText(rs.getString("text"));
        comment.setAuthor_id(rs.getLong("author_id"));
        comment.setAnnouncement_id(rs.getLong("announcement_id"));
        comment.setUser_assignment_id(rs.getLong("user_assignment_id"));
        comment.setMaterial_id(rs.getLong("material_id"));
        comment.setDate(rs.getDate("date").toLocalDate().atStartOfDay());

        return comment;
    }
}


