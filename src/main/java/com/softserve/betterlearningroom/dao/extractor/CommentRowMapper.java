package com.softserve.betterlearningroom.dao.extractor;

import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.entity.User;
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
        comment.setAuthor((User) rs.getObject("author"));
        comment.setDate(LocalDateTime.parse(rs.getString("date")));

        return comment;
    }
}


