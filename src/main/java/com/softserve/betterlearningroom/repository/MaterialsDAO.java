package com.softserve.betterlearningroom.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MaterialsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Material> getById(Long materialId) {
        return jdbcTemplate.query("SELECT * FROM materials where materialid=?", new Object[] { materialId }, new MaterialRowMapper());
    }

//    public void create(Materials materials) {
//        String sql = "INSERT INTO Materials (materialId, userId, assignmentStatus, submissionDate, " +
//                "grade, feedback) VALUES (:materialId, :userId, :assignmentStatus, :submissionDate, :grade, :feedback)";
//        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(materials);
//        jdbcTemplate.update(sql, parameterSource);
//    }
//
//    public Materials readById(long id) {
//        String sql = "SELECT * FROM Materials WHERE id=:id";
//        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
//        return jdbcTemplate.queryForObject(sql, parameterSource, BeanPropertyRowMapper.newInstance(Materials.class));
//    }
//
//    public void update(Materials materials, long id) {
//        String sql = "UPDATE Materials SET materialId=:materialId, userId=:userId, assignmentStatus=:assignmentStatus, " +
//                "submissionDate=:submissionDate, grade=:grade, feedback=:feedback WHERE id=:id";
//        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(materials);
//        jdbcTemplate.update(sql, parameterSource);
//    }
//
//    public List<Materials> getAll() {
//        String sql = "SELECT * FROM Materials";
//        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Materials.class));
//    }
//
//    public List<Materials> getByAssignment(long assignmentId) {
//        String sql = "SELECT * FROM Materials WHERE assignmentId=:assignmentId";
//        SqlParameterSource parameterSource = new MapSqlParameterSource("assignmentId", assignmentId);
//        return jdbcTemplate.query(sql, parameterSource, BeanPropertyRowMapper.newInstance(Materials.class));
//    }
//
//    public List<Materials> getByStudent(long studentId) {
//        String sql = "SELECT * FROM Materials WHERE studentId=:studentId";
//        SqlParameterSource parameterSource = new MapSqlParameterSource("studentId", studentId);
//        return jdbcTemplate.query(sql, parameterSource, BeanPropertyRowMapper.newInstance(Materials.class));
//    }
}



