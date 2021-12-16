package com.softserve.betterlearningroom.mapper;

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
        classroom.setClassroom_id(rs.getLong("classroom_id"));
        classroom.setTitle(rs.getString("title"));
        classroom.setSession(rs.getString("session"));
        classroom.setDescription(rs.getString("description"));
        classroom.setCode(rs.getString("code"));
        classroom.setUser_id(rs.getLong("user_id"));
        classroom.setTeachers((List<User>) rs.getArray("teachers"));
        classroom.setStudents((List<User>) rs.getArray("students"));
        classroom.setTopics((List<Topic>) rs.getArray("topics"));
        classroom.setAnnouncements((List<Announcement>) rs.getArray("announcements"));

        return classroom;
    }
}
