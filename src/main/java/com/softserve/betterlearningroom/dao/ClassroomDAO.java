package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@PropertySource(value = "classpath:/classroomQuery.properties")
public class ClassroomDAO {

    private final NamedParameterJdbcTemplate jdbcParameterTemplate;

    @Value("${getClassroomById}")
    private String getClassroomById;

    @Value("${getClassroomTeachers}")
    private String getClassroomTeachers;

    @Value("${getClassroomOwnerById}")
    private String getClassroomOwnerById;

    @Value("${getClassroomsByTeacher}")
    private String getClassroomsByTeacher;

    @Value("${getClassroomsByStudent}")
    private String getClassroomsByStudent;

    @Value("${createClassroom}")
    private String createClassroom;

    @Value("${removeClassroom}")
    private String removeClassroom;

    public Classroom getClassroomById(Long classroomId){
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroomId", classroomId);
        return jdbcParameterTemplate.queryForObject(getClassroomById, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class));
    }

    public List<User> getClassroomTeachers(Long classroomId){
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroomId", classroomId);
        return jdbcParameterTemplate.query(getClassroomTeachers, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
    }

    public User getClassroomOwnerById(Long classroomId){
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroomId", classroomId);
        return jdbcParameterTemplate.queryForObject(getClassroomOwnerById, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
    }

    public List<Classroom> getClassroomsByTeacher(Long userId){
        SqlParameterSource parameterSource = new MapSqlParameterSource("userId", userId);
        return jdbcParameterTemplate.query(getClassroomsByTeacher, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class));
    }

    public List<Classroom> getClassroomsByStudent(Long userId){
        SqlParameterSource parameterSource = new MapSqlParameterSource("userId", userId);
        return jdbcParameterTemplate.query(getClassroomsByStudent, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class));
    }

    public void createClassroom(Classroom classroom){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("classroomId", classroom.getClassroomId())
                .addValue("userId", classroom.getUserId())
                .addValue("title", classroom.getTitle())
                .addValue("session", classroom.getSession())
                .addValue("description", classroom.getDescription())
                .addValue("code", classroom.getCode());
        jdbcParameterTemplate.update(createClassroom, params);
    }

    public  void removeClassroomById(Long classroomId){
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroomId", classroomId);
        jdbcParameterTemplate.update(removeClassroom, parameterSource);

    }
}
