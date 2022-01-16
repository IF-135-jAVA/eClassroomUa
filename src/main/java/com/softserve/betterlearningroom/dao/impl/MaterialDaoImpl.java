package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.MaterialDao;
import com.softserve.betterlearningroom.dao.extractor.MaterialRowMapper;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.MaterialType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:db/materials/materialQuery.properties")
public class MaterialDaoImpl implements MaterialDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    public Material readById(Long materialId) {
        return namedParameterJdbcTemplate.query(getByIdQuery, new MapSqlParameterSource("materialid", materialId), new MaterialRowMapper()).stream().findFirst().orElse(null);
    }

    public List<Material> readAllByClassroom(Long classroomId) {
        return namedParameterJdbcTemplate.query(getAllQuery, new MapSqlParameterSource("topicid", classroomId), new MaterialRowMapper());
    }

    public List<Material> readAllByTopic(Long classroomId, Long topicId) {
        return readAllByClassroom(classroomId).stream()
                .filter(material -> material.getId().equals(topicId))
                .collect(Collectors.toList());
    }

    public List<Material> readAllByName(Long classroomId, String name) {
        return readAllByClassroom(classroomId).stream()
                .filter(material -> material.getTitle().contains(name))
                .collect(Collectors.toList());
    }

    public List<Material> readAllByType(Long classroomId, MaterialType materialType) {
        return readAllByClassroom(classroomId).stream()
                .filter(material -> material.getMaterialType().equals(materialType))
                .collect(Collectors.toList());
    }

    public Material create(Material material, Long topicId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        MaterialType type = material.getMaterialType();
        param.addValue("materialtype", type.name());
        param.addValue("materialtext", material.getText());
        param.addValue("startdate", material.getStartDate());
        param.addValue("duedate", material.getDueDate());
        param.addValue("maxscore", material.getMaxScore());
        param.addValue("task", material.getTask());
        param.addValue("testurl", material.getUrl());
        param.addValue("title", material.getTitle());
        param.addValue("topicid", material.getTopicId());
        namedParameterJdbcTemplate.update(addQuery, param);
        return readAllByName(material.getClassroomId(), material.getTitle())
                .stream()
                .findFirst().orElse(null);
    }

    public Material update(Material material) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        MaterialType type = material.getMaterialType();
        param.addValue("materialid", material.getId());
        param.addValue("materialtext", material.getText());
        param.addValue("materialtype", type.name());
        param.addValue("startdate", material.getStartDate());
        param.addValue("duedate", material.getDueDate());
        param.addValue("maxscore", material.getMaxScore());
        param.addValue("task", material.getTask());
        param.addValue("testurl", material.getUrl());
        param.addValue("title", material.getTitle());
        namedParameterJdbcTemplate.update(updateQuery, param);
        return readById(material.getId());
    }

    public int delete(Long materialId) {
        return namedParameterJdbcTemplate.update(removeQuery, new MapSqlParameterSource("materialid", materialId));
    }

    public int delete(Material material) {
        return delete(material.getId());
    }

}
