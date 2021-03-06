package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.MaterialDAO;
import com.softserve.betterlearningroom.dao.extractor.MaterialRowMapper;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.MaterialType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class MaterialDAOImpl implements MaterialDAO {

    private static final String MATERIALID2 = "materialid";

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

    @Override
    public Material findById(Long materialId) {
        return namedParameterJdbcTemplate.query(getByIdQuery, new MapSqlParameterSource(MATERIALID2, materialId), new MaterialRowMapper()).stream().findFirst().orElse(null);
    }

    @Override
    public List<Material> findAllByClassroomId(String classroomId) {
        return namedParameterJdbcTemplate.query(getAllQuery, new MapSqlParameterSource("classroomid", classroomId), new MaterialRowMapper());
    }

    @Override
    public List<Material> findAllByClassroomIdAndTopicId(String classroomId, Long topicId) {
        return findAllByClassroomId(classroomId).stream()
                .filter(material -> material.getId().equals(topicId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Material> findAllByClassroomIdAndName(String classroomId, String name) {
        return findAllByClassroomId(classroomId).stream()
                .filter(material -> material.getTitle().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Material> findAllByClassroomIdAndType(String classroomId, MaterialType materialType) {
        return findAllByClassroomId(classroomId).stream()
                .filter(material -> material.getMaterialType().equals(materialType))
                .collect(Collectors.toList());
    }

    @Override
    public Material save(Material material, Long topicId) {
        log.info(material.toString());
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
        param.addValue("topicid", topicId);
        namedParameterJdbcTemplate.update(addQuery, param);
        return material;
    }

    @Override
    public Material update(Material material) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        MaterialType type = material.getMaterialType();
        param.addValue(MATERIALID2, material.getId());
        param.addValue("materialtext", material.getText());
        param.addValue("materialtype", type.name());
        param.addValue("startdate", material.getStartDate());
        param.addValue("duedate", material.getDueDate());
        param.addValue("maxscore", material.getMaxScore());
        param.addValue("task", material.getTask());
        param.addValue("testurl", material.getUrl());
        param.addValue("title", material.getTitle());
        namedParameterJdbcTemplate.update(updateQuery, param);
        return findById(material.getId());
    }

    @Override
    public int delete(Long materialId) {
        return namedParameterJdbcTemplate.update(removeQuery, new MapSqlParameterSource(MATERIALID2, materialId));
    }
}
