package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Criterion;
import com.softserve.betterlearningroom.entity.Level;
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
@PropertySource("classpath:levelQuery.properties")
public class LevelDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${get.all}")
    private String getAllQuery;

    @Value("${add.new}")
    private String addQuery;

    @Value("${update}")
    private String updateQuery;

    @Value("${remove}")
    private String removeQuery;

    public List<Level> getAllLevels(Long criterionId) {
        return jdbcTemplate.query(getAllQuery, new MapSqlParameterSource("criterionid", criterionId), BeanPropertyRowMapper.newInstance(Level.class));
    }

    public List<Level> getAllLevels(Criterion criterion) {
        return getAllLevels(criterion.getId());
    }

    public int addLevel(Level level, Long criterionId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("criterionid", criterionId);
        param.addValue("description", level.getDescription());
        param.addValue("title", level.getTitle());
        param.addValue("mark", level.getMark());
        return jdbcTemplate.update(addQuery, param);
    }

    public int addLevel(Level level, Criterion criterion) {
        return addLevel(level, criterion.getId());
    }

    public int updateLevel(Level level) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("levelid", level.getId());
        param.addValue("description", level.getDescription());
        param.addValue("title", level.getTitle());
        param.addValue("mark", level.getMark());
        return jdbcTemplate.update(updateQuery, param);
    }

    public int removeLevel(Long levelId) {
        return jdbcTemplate.update(removeQuery, new MapSqlParameterSource("leveid", levelId));
    }

    public int removeLevel(Level level) {
        return removeLevel(level.getId());
    }

}