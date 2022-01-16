package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.ClassroomDao;
import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@PropertySource(value = "classpath:/db/classrooms/classroomQuery.properties")
public class ClassroomDaoImpl implements ClassroomDao {

    private final NamedParameterJdbcTemplate jdbcParameterTemplate;

    @Value("${getClassroomById}")
    private String getClassroomById;

    @Value("${getClassroomTeachers}")
    private String getClassroomTeachers;

    @Value("${getClassroomStudents}")
    private String getClassroomStudents;

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

    @Value("${getClassroomByCode}")
    private String getClassroomByCode;

    @Value("${joinClassroomAsStudent}")
    private String joinClassroomAsStudent;

    @Value("${joinClassroomAsTeacher}")
    private String joinClassroomAsTeacher;

    @Override
    public Classroom getClassroomById(Long classroomId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroomId", classroomId);
        return jdbcParameterTemplate.queryForObject(getClassroomById, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class));
    }

    @Override
    public List<User> getClassroomTeachers(Long classroomId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroomId", classroomId);
        return jdbcParameterTemplate.query(getClassroomTeachers, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public List<User> getClassroomStudents(Long classroomId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroomId", classroomId);
        return jdbcParameterTemplate.query(getClassroomStudents, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public User getClassroomOwnerById(Long classroomId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroomId", classroomId);
        return jdbcParameterTemplate.queryForObject(getClassroomOwnerById, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public List<Classroom> getClassroomsByTeacher(Long userId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("userId", userId);
        return jdbcParameterTemplate.query(getClassroomsByTeacher, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class));
    }

    @Override
    public List<Classroom> getClassroomsByStudent(Long userId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("userId", userId);
        return jdbcParameterTemplate.query(getClassroomsByStudent, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class));
    }

    @Override
    public Classroom joinClassroomAsStudent(String code, Long userId) {
        Classroom classroom = getClassroomByCode(code);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("classroomId", classroom.getClassroomId());
        parameterSource.addValue("userId", userId);
        jdbcParameterTemplate.update(joinClassroomAsStudent, parameterSource);
        return classroom;
    }

    @Override
    public Classroom joinClassroomAsTeacher(String code, Long userId) {
        Classroom classroom = getClassroomByCode(code);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("classroomId", classroom.getClassroomId());
        parameterSource.addValue("userId", userId);
        jdbcParameterTemplate.update(joinClassroomAsTeacher, parameterSource);
        return classroom;
    }

    @Override
    public Classroom createClassroom(Classroom classroom) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("classroomId", classroom.getClassroomId())
                .addValue("userId", classroom.getUserId())
                .addValue("title", classroom.getTitle())
                .addValue("session", classroom.getSession())
                .addValue("description", classroom.getDescription())
                .addValue("code", classroom.getCode());
        jdbcParameterTemplate.update(createClassroom, params);
        return classroom;
    }

    @Override
    public void removeClassroomById(Long classroomId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("classroomId", classroomId);
        jdbcParameterTemplate.update(removeClassroom, parameterSource);
    }

    @Override
    public Classroom getClassroomByCode(String code) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("code", code);
        return jdbcParameterTemplate.queryForObject(getClassroomByCode, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class));
    }
}
