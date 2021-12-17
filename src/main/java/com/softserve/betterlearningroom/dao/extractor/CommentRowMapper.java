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
        comment.setId(rs.getInt("id"));
        comment.setText(rs.getString("text"));
        comment.setAuthor_id((User) rs.getObject("author_id"));
        comment.setAnnouncement_id((Announcement) rs.getObject("announcement_id"));
        comment.setUser_assignment_id((Assignment) rs.getObject("user_assignment_id"));
        comment.setMaterial_id((Material) rs.getObject("material_id"));
        comment.setDate(LocalDateTime.parse(rs.getString("date")));

        return comment;
    }
}


