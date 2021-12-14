package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@PropertySource(value = "classpath:/classroomQuery.properties")
public class ClassroomDAO {

    private NamedParameterJdbcTemplate jdbcParameterTemplate;

    @Value("${getClassroomById}")
    private String getClassroomById;

    @Value("${getClassroomTeachers}")
    private String getClassroomTeachers;

    @Value("${getClassroomOwnerId}")
    private String getClassroomOwnerById;

    @Value("${createClassroom}")
    private String createClassroom;

    @Value("${removeClassroom}")
    private String removeClassroom;

    public Classroom getClassroomById(Long classroomId){
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroomId", classroomId);
        return jdbcParameterTemplate.queryForObject(getClassroomById, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class));
    }

    public List<User> getClassroomTeachers(Long classroomId){
        return jdbcParameterTemplate.query(getClassroomTeachers, BeanPropertyRowMapper.newInstance(User.class));
    }

    public User getClassroomOwnerById(Long ownerId){
        SqlParameterSource parameterSource = new MapSqlParameterSource("ownerId", ownerId);
        return jdbcParameterTemplate.queryForObject(getClassroomOwnerById, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
    }

    public void createClassroom(Classroom classroom){
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(classroom);
        jdbcParameterTemplate.update(createClassroom, parameterSource);
    }

    public  void removeClassroomById(Long classroomId){
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroomId", classroomId);
        jdbcParameterTemplate.update(removeClassroom, parameterSource);

    }
}
