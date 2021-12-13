package com.softserwe.betterlearningroom.mapper;

import com.softserwe.betterlearningroom.entity.Announcment;
import com.softserwe.betterlearningroom.entity.Classroom;
import com.softserwe.betterlearningroom.entity.Topic;
import com.softserwe.betterlearningroom.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ClassroomRowMapper implements RowMapper<Classroom> {

    @Override
    public Classroom mapRow(ResultSet rs, int rowNum) throws SQLException {
        Classroom classroom = new Classroom();
        classroom.setClassroomId(rs.getLong("classroomId"));
        classroom.setClassroomTitle(rs.getString("classroomTitle"));
        classroom.setClassroomSession(rs.getString("classroomSession"));
        classroom.setClassroomDescription(rs.getString("classroomDescription"));
        classroom.setClassroomCode(rs.getString("classroomCode"));
        classroom.setClassroomOwner((User) rs.getArray("classroomOwner"));
        classroom.setClassroomTeachers((List<User>) rs.getArray("classroomTeachers"));
        classroom.setClassroomStudents((List<User>) rs.getArray("classroomStudents"));
        classroom.setClassroomTopics((List<Topic>) rs.getArray("classroomTopics"));
        classroom.setClassroomAnnouncements((List<Announcment>) rs.getArray("classroomAnnouncements"));

        return classroom;
    }
}
