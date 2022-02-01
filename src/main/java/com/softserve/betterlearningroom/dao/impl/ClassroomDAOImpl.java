package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.ClassroomDAO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
@RequiredArgsConstructor
@PropertySource(value = "classpath:/db/classrooms/classroomQuery.properties")
public class ClassroomDAOImpl implements ClassroomDAO {

    private static final String USER_ID = "userId";

    private static final String CLASSROOM_ID = "classroomId";

    private final NamedParameterJdbcTemplate jdbcParameterTemplate;

    @Value("${getClassroomById}")
    private String getClassroomById;

    @Value("${getClassroomByOwnerId}")
    private String getClassroomByOwnerId;

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
    public Classroom findClassroomById(Long classroomId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(CLASSROOM_ID, classroomId);
        return jdbcParameterTemplate.queryForObject(getClassroomById, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class));
    }

    @Override
    public List<User> getAllTeachersById(Long classroomId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(CLASSROOM_ID, classroomId);
        return jdbcParameterTemplate.query(getClassroomTeachers, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public List<User> getAllStudentsById(Long classroomId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(CLASSROOM_ID, classroomId);
        return jdbcParameterTemplate.query(getClassroomStudents, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public User getClassroomOwnerById(Long classroomId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(CLASSROOM_ID, classroomId);
        return jdbcParameterTemplate.queryForObject(getClassroomOwnerById, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public List<Classroom> findAllClassroomsByTeacherId(Long userId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(USER_ID, userId);
        List<Classroom> classrooms = new ArrayList<>();
        classrooms.addAll(jdbcParameterTemplate.query(getClassroomsByTeacher, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class)));
        return classrooms;
    }

    @Override
    public List<Classroom> findAllClassroomsByStudentId(Long userId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(USER_ID, userId);
        List<Classroom> classrooms = new ArrayList<>();
        classrooms.addAll(jdbcParameterTemplate.query(getClassroomsByStudent, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class)));
        return classrooms;
    }

    @Override
    public Classroom joinClassroomAsStudent(String code, Long userId) {
        Classroom classroom = findByCode(code);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(USER_ID, userId);
        parameterSource.addValue(CLASSROOM_ID, classroom.getClassroomId());
        jdbcParameterTemplate.update(joinClassroomAsStudent, parameterSource);
        return classroom;
    }

    @Override
    public Classroom joinClassroomAsTeacher(String code, Long userId) {
        Classroom classroom = findByCode(code);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(USER_ID, userId);
        parameterSource.addValue(CLASSROOM_ID, classroom.getClassroomId());
        jdbcParameterTemplate.update(joinClassroomAsTeacher, parameterSource);
        return classroom;
    }

    @Override
    public Classroom save(Classroom classroom) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(USER_ID, classroom.getUserId())
                .addValue("title", classroom.getTitle())
                .addValue("session", classroom.getSession())
                .addValue("description", classroom.getDescription())
                .addValue("code", ClassroomDAOImpl.generatingRandomClassroomStringCode());
        jdbcParameterTemplate.update(createClassroom, params);
        return classroom;
    }

    @Override
    public void delete(Long classroomId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(CLASSROOM_ID, classroomId);
        jdbcParameterTemplate.update(removeClassroom, parameterSource);
    }

    @Override
    public Classroom findByCode(String code) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("code", code);
        return jdbcParameterTemplate.queryForObject(getClassroomByCode, parameterSource, BeanPropertyRowMapper.newInstance(Classroom.class));
    }

    private static String generatingRandomClassroomStringCode() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
