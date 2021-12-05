package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.UserAssignment;
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
public class UserAssignmentDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public void create(UserAssignment userAssignment) {
        String sql = "INSERT INTO user_assignment (material_id, user_id, assignment_status, submission_date, " +
                "grade, feedback) VALUES (:materialId, :userId, :assignmentStatus, :submissionDate, :grade, :feedback)";
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userAssignment);
        jdbcTemplate.update(sql, parameterSource);
    }

    public List<UserAssignment> readById(long id) {
        String sql = "SELECT id, material_id, user_id, assignment_status, submission_date, grade, feedback " +
                "FROM user_assignment WHERE id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.query(sql, parameterSource, BeanPropertyRowMapper.newInstance(UserAssignment.class));
    }

    public void update(UserAssignment userAssignment) {
        String sql = "UPDATE user_assignment SET material_id=:materialId, user_id=:userId, assignment_status=:assignmentStatus, " +
                "submission_date=:submissionDate, grade=:grade, feedback=:feedback WHERE id=:id";
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userAssignment);
        jdbcTemplate.update(sql, parameterSource);
    }

    public List<UserAssignment> getByAssignment(long assignmentId) {
        String sql = "SELECT id, material_id, user_id, assignment_status, submission_date, grade, feedback " +
                "FROM user_assignment WHERE material_id=:assignmentId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("assignmentId", assignmentId);
        return jdbcTemplate.query(sql, parameterSource, BeanPropertyRowMapper.newInstance(UserAssignment.class));
    }

    public List<UserAssignment> getByStudent(long studentId) {
        String sql = "SELECT id, material_id, user_id, assignment_status, submission_date, grade, feedback " +
                "FROM user_assignment WHERE student_id=:studentId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("studentId", studentId);
        return jdbcTemplate.query(sql, parameterSource, BeanPropertyRowMapper.newInstance(UserAssignment.class));
    }
}
