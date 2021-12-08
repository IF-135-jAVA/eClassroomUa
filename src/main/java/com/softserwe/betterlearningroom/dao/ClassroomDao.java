package com.softserwe.betterlearningroom.dao;

import com.softserwe.betterlearningroom.model.Classroom;
import com.softserwe.betterlearningroom.model.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ClassroomDao {
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Classroom> getClassroomById(Long classroomId){
        String sql = "SELECT classroom_id, classroom_title, classroom_session, description, code, classroom_owner, classroom_student" +
                "FROM classroom WHERE classroom_id=:classroomId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroomId", classroomId);
        return jdbcTemplate.query(sql, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class));
    }

    public List<User> getClassroomTeachers(Long classroomId){
        String sql = "SELECT classroom_id, classroom_title, classroom_session, description, code, classroom_owner, classroom_teacher, classroom_student" +
                "FROM classroom WHERE classroom_id=:classroomId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroomId", classroomId);
        return jdbcTemplate.query(sql, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
    }

    public List<User> getClassroomOwner(Long ownerId){
        String sql = "SELECT classroom_id, classroom_title, classroom_session, description, code, classroom_owner, classroom_teacher, classroom_student" +
                "FROM classroom WHERE ownerId=:ownerId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("ownerId", ownerId);
        return jdbcTemplate.query(sql, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
    }

    public void addClassroom(Classroom classroom){
        String sql = "INSERT INTO classroom (classroom_id, classroom_title, classroom_sesion, description, code, classroom_owner, classroom_teacher, classroom_student" +
                "VALUES (:classroomId, :classroomTitle, :classroomSession, :description, :code, :classroomOwner, :clasroomTeachers, :classroomStudents)";
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(classroom);
        jdbcTemplate.update(sql, parameterSource);
    }
}
