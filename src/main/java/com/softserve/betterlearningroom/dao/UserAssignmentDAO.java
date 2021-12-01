package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.model.UserAssignment;
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
public class UserAssignmentDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public void create(UserAssignment userAssignment) {
        String sql = "INSERT INTO UserAssignment (materialId, userId, assignmentStatus, submissionDate, " +
                "grade, feedback) VALUES (:materialId, :userId, :assignmentStatus, :submissionDate, :grade, :feedback)";
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userAssignment);
        jdbcTemplate.update(sql, parameterSource);
    }

    public UserAssignment readById(long id) {
        String sql = "SELECT * FROM UserAssignment WHERE id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(sql, parameterSource, BeanPropertyRowMapper.newInstance(UserAssignment.class));
    }

    public void update(UserAssignment userAssignment, long id) {
        String sql = "UPDATE UserAssignment SET materialId=:materialId, userId=:userId, assignmentStatus=:assignmentStatus, " +
                "submissionDate=:submissionDate, grade=:grade, feedback=:feedback WHERE id=:id";
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userAssignment);
        jdbcTemplate.update(sql, parameterSource);
    }

    public List<UserAssignment> getAll() {
        String sql = "SELECT * FROM UserAssignment";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UserAssignment.class));
    }

    public List<UserAssignment> getByAssignment(long assignmentId) {
        String sql = "SELECT * FROM UserAssignment WHERE assignmentId=:assignmentId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("assignmentId", assignmentId);
        return jdbcTemplate.query(sql, parameterSource, BeanPropertyRowMapper.newInstance(UserAssignment.class));
    }

    public List<UserAssignment> getByStudent(long studentId) {
        String sql = "SELECT * FROM UserAssignment WHERE studentId=:studentId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("studentId", studentId);
        return jdbcTemplate.query(sql, parameterSource, BeanPropertyRowMapper.newInstance(UserAssignment.class));
    }
}
