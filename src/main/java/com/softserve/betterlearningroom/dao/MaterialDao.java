package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.*;
import com.softserve.betterlearningroom.dao.extractor.MaterialRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:materialQuery.properties")
public class MaterialDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${get.all.materials}")
    private String getAllQuery;

    @Value("${get.by.id.material}")
    private String getByIdQuery;

    @Value("${add.new.material}")
    private String addQuery;

    @Value("${update.material}")
    private String updateQuery;

    @Value("${remove.material}")
    private String removeQuery;

    @Autowired
    public MaterialDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Material getById(Long materialId) {
        return jdbcTemplate.query(getByIdQuery, new MapSqlParameterSource("materialid", materialId), new MaterialRowMapper()).stream().findFirst().orElse(null);
    }

    public List<Material> getAllByClassroom(Long classroomId) {
        return jdbcTemplate.query(getAllQuery, new MapSqlParameterSource("topicid", classroomId), new MaterialRowMapper());
    }

    public List<Material> getAllByTopic(Long classroomId, Long topicId) {
        return getAllByClassroom(classroomId).stream()
                .filter(material -> material.getId().equals(topicId))
                .collect(Collectors.toList());
    }

    public List<Material> getAllByName(Long classroomId, String name) {
        return getAllByClassroom(classroomId).stream()
                .filter(material -> material.getTitle().contains(name))
                .collect(Collectors.toList());
    }

    public List<Material> getAllByType(Long classroomId, MaterialType materialType) {
        return getAllByClassroom(classroomId).stream()
                .filter(material -> material.getMaterialType().equals(materialType))
                .collect(Collectors.toList());
    }

    public Material addMaterial(Material material, Long topicId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        MaterialType type = material.getMaterialType();
        param.addValue("materialtype", type.name());
        param.addValue("materialtext", material.getText());
        param.addValue("startdate", material.getStartDate().toLocalDate());
        param.addValue("duedate", material.getDueDate().toLocalDate());
        param.addValue("maxscore", material.getMaxScore());
        param.addValue("task", material.getTask());
        param.addValue("testurl", material.getUrl());
        param.addValue("title", material.getTitle());
        param.addValue("topicid", material.getTopicId());
        jdbcTemplate.update(addQuery, param);
        return getAllByName(material.getClassroomId(), material.getTitle())
                .stream()
                .findFirst().orElse(null);
    }

    public Material updateMaterial(Material material) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        MaterialType type = material.getMaterialType();
        param.addValue("materialid", material.getId());
        param.addValue("materialtext", material.getText());
        param.addValue("materialtype", type.name());
        param.addValue("startdate", material.getStartDate().toLocalDate());
        param.addValue("duedate", material.getDueDate().toLocalDate());
        param.addValue("maxscore", material.getMaxScore());
        param.addValue("task", material.getTask());
        param.addValue("testurl", material.getUrl());
        param.addValue("title", material.getTitle());
        jdbcTemplate.update(updateQuery, param);
        return getById(material.getId());
    }

    public int removeMaterial(Long materialId) {
        return jdbcTemplate.update(removeQuery, new MapSqlParameterSource("materialid", materialId));
    }

    public int removeMaterial(Material material) {
        return removeMaterial(material.getId());
    }

}
