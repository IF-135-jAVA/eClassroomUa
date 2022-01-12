package com.softserve.betterlearningroom.dao.extractor;

import com.softserve.betterlearningroom.entity.Announcement;
import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.Topic;
import com.softserve.betterlearningroom.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ClassroomRowMapper implements RowMapper<Classroom>{

    @Override
    public Classroom mapRow(ResultSet rs, int rowNum) throws SQLException {
        Classroom classroom = new Classroom();
        classroom.setClassroomId(rs.getLong("classroomId"));
        classroom.setUserId(rs.getLong("userId"));
        classroom.setTitle(rs.getString("title"));
        classroom.setSession(rs.getString("session"));
        classroom.setDescription(rs.getString("description"));
        classroom.setCode(rs.getString("code"));
        classroom.setOwner((User) rs.getObject("owner"));
        classroom.setTeachers((List<User>) rs.getArray("teachers"));
        classroom.setStudents((List<User>) rs.getArray("students"));
        classroom.setTopics((List<Topic>) rs.getArray("topics"));
        classroom.setAnnouncements((List<Announcement>) rs.getArray("announcements"));

        return classroom;
    }
}
