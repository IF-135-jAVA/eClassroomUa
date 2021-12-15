package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.*;
import com.softserve.betterlearningroom.dao.extractor.MaterialRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:materialQuery.properties")
public class MaterialDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${get.all}")
    private String getAllQuery;

    @Value("${get.by.id}")
    private String getByIdQuery;

    @Value("${add.new}")
    private String addQuery;

    @Value("${update}")
    private String updateQuery;

    @Value("${remove}")
    private String removeQuery;

    public Material getById(Long materialId) {
        return jdbcTemplate.query(getByIdQuery, new MapSqlParameterSource("materialid", materialId), new MaterialRowMapper()).stream().findFirst().orElse(null);
    }

    public List<Material> getAllByClassroom(Long classroomId) {
        return jdbcTemplate.query(getAllQuery, new MapSqlParameterSource("materialid", classroomId), new MaterialRowMapper());
    }

    public List<Material> getAllByTopic(Long classroomId, Long topicId) {
        return getAllByClassroom(classroomId).stream()
                .filter(material -> material.getId() == topicId)
                .collect(Collectors.toList());
    }

    public List<Material> getAllByName(Long classroomId, String name) {
        return getAllByClassroom(classroomId).stream()
                .filter(material -> material.getText().contains(name))
                .collect(Collectors.toList());
    }

    public List<Material> getAllByType(Long classroomId, MaterialType materialType) {
        return getAllByClassroom(classroomId).stream()
                .filter(material -> material.getMaterialType().equals(materialType))
                .collect(Collectors.toList());
    }

    public int addMaterial(Material material, Long topicId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        MaterialType type = material.getMaterialType();
        param.addValue("materialType", type);
        param.addValue("materialtext", material.getText());
        switch(type){
            case QUESTIONS:
                param.addValue("startdate", material.getStartDate().toLocalDate());
                param.addValue("duedate", material.getDueDate().toLocalDate());
                param.addValue("maxScore", material.getMaxScore());
                break;
            case TASK:
                param.addValue("startdate", material.getStartDate().toLocalDate());
                param.addValue("duedate", material.getDueDate().toLocalDate());
                param.addValue("maxScore", material.getMaxScore());
                param.addValue("task", material.getTask());
                break;
            case TEST:
                param.addValue("startdate", material.getStartDate().toLocalDate());
                param.addValue("duedate", material.getDueDate().toLocalDate());
                param.addValue("maxScore", material.getMaxScore());
                param.addValue("testUrl", material.getUrl());
                break;

        }
        return jdbcTemplate.update(addQuery, param);
    }

    public int updateMaterial(Material material) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        MaterialType type = material.getMaterialType();
        param.addValue("materialid", material.getId());
        param.addValue("materialtext", material.getText());
        param.addValue("materialType", type);
        switch(type){
            case QUESTIONS:
                param.addValue("startdate", material.getStartDate().toLocalDate());
                param.addValue("duedate", material.getDueDate().toLocalDate());
                param.addValue("maxScore", material.getMaxScore());
                break;
            case TASK:
                param.addValue("startdate", material.getStartDate().toLocalDate());
                param.addValue("duedate", material.getDueDate().toLocalDate());
                param.addValue("maxScore", material.getMaxScore());
                param.addValue("task", material.getTask());
                break;
            case TEST:
                param.addValue("startdate", material.getStartDate().toLocalDate());
                param.addValue("duedate", material.getDueDate().toLocalDate());
                param.addValue("maxScore", material.getMaxScore());
                param.addValue("testUrl", material.getUrl());
                break;

        }
        return jdbcTemplate.update(updateQuery, param);
    }

    public int removeMaterial(Long materialId) {
        return jdbcTemplate.update(removeQuery, new MapSqlParameterSource("materialid", materialId));
    }

    public int removeMaterial(Material material) {
        return removeMaterial(material.getId());
    }

}
