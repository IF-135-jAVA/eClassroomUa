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

    @Value("${createClassroom}")
    private String createClassroom;

    @Value("${removeClassroom}")
    private String removeClassroom;

    public Classroom getClassroomById(Long classroom_id){
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroom_id", classroom_id);
        return jdbcParameterTemplate.queryForObject(getClassroomById, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class));
    }

    public List<User> getClassroomTeachers(Long classroom_id){
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroom_id", classroom_id);
        return jdbcParameterTemplate.query(getClassroomTeachers, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
    }

    public User getClassroomOwnerById(Long user_id){
        SqlParameterSource parameterSource = new MapSqlParameterSource("user_id", user_id);
        return jdbcParameterTemplate.queryForObject(getClassroomOwnerById, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
    }

    public void createClassroom(Classroom classroom){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("classroom_id", classroom.getClassroom_id())
                .addValue("user_id", classroom.getUser_id())
                .addValue("title", classroom.getTitle())
                .addValue("session", classroom.getSession())
                .addValue("description", classroom.getDescription())
                .addValue("code", classroom.getCode());
        jdbcParameterTemplate.update(createClassroom, params);
    }

    public  void removeClassroomById(Long classroom_id){
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroom_id", classroom_id);
        jdbcParameterTemplate.update(removeClassroom, parameterSource);

    }
}
