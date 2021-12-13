package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.dao.extractor.CriterionRowMapper;
import com.softserve.betterlearningroom.entity.Criterion;
import com.softserve.betterlearningroom.entity.Material;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:criterionQuery.properties")
public class CriterionDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${get.all}")
    private String getAllQuery;

    @Value("${add.new}")
    private String addQuery;

    @Value("${update}")
    private String updateQuery;

    @Value("${remove}")
    private String removeQuery;

    public List<Criterion> getAllCriterions(Long materialId) {
        return jdbcTemplate.query(getAllQuery, new MapSqlParameterSource("materialid", materialId), new CriterionRowMapper());
    }
    public List<Criterion> getAllCriterions(Material material) {
        return getAllCriterions(material.getId());
    }

    public int addCriterion(Criterion criterion, Long materialId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("materialid", materialId);
        param.addValue("description", criterion.getDescription());
        param.addValue("title", criterion.getTitle());
        return jdbcTemplate.update(addQuery, param);
    }

    public int addCriterion(Criterion criterion, Material material) {
        return addCriterion(criterion, material.getId());
    }

    public int updateCriterion(Criterion criterion) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("criterionid", criterion.getId());
        param.addValue("description", criterion.getDescription());
        param.addValue("title", criterion.getTitle());
        return jdbcTemplate.update(updateQuery, param);
    }

    public int removeCriterion(Long criterionId) {
        return jdbcTemplate.update(removeQuery, new MapSqlParameterSource("criterionid", criterionId));
    }

    public int removeCriterion(Criterion criterion) {
        return removeCriterion(criterion.getId());
    }

}
