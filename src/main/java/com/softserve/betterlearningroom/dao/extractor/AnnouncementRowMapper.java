package com.softserve.betterlearningroom.dao.extractor;

import com.softserve.betterlearningroom.entity.Announcement;
import com.softserve.betterlearningroom.entity.Comment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class AnnouncementRowMapper implements RowMapper<Announcement> {

    @Override
    public Announcement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Announcement announcement = new Announcement();
        announcement.setId(rs.getLong("id"));
        announcement.setCourseId(rs.getString("courseId"));
        announcement.setText(rs.getString("text"));
        announcement.setComments((List<Comment>) rs.getArray("comments"));
        announcement.setEnabled(rs.getBoolean("enabled"));
        return announcement;
    }
    
}
