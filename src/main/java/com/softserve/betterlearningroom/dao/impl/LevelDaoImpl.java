package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.LevelDao;
import com.softserve.betterlearningroom.entity.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@PropertySource(value = "classpath:db/level/levelQuery.properties")
public class LevelDaoImpl implements LevelDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${level.save}")
    private String saveQuery;

    @Value("${level.update}")
    private String updateQuery;

    @Value("${level.findAll}")
    private String findAllQuery;

    @Value("${level.findAll.deleted}")
    private String findAllDeletedQuery;

    @Value("${level.findById}")
    private String findByIdQuery;

    @Value("${level.removeById}")
    private String removeByIdQuery;

    @Override
    public Level save(Level level) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource
                .addValue("level_id", level.getLevelId())
                .addValue("title", level.getTitle())
                .addValue("description", level.getDescription())
                .addValue("criterion_id", level.getCriterionId())
                .addValue("mark", level.getMark());
        jdbcTemplate.update(saveQuery, parameterSource);
        return level;
    }

    @Override
    public Level update(Level level) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(level);
        jdbcTemplate.update(updateQuery, parameterSource);
        return level;
    }

    @Override
    public List<Level> findAll() {
        return jdbcTemplate.query(findAllQuery,
                BeanPropertyRowMapper.newInstance(Level.class));
    }

    @Override
    public Level findById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("level_id", id);
        return jdbcTemplate.queryForObject(findByIdQuery, parameterSource,
                BeanPropertyRowMapper.newInstance(Level.class));
    }

    @Override
    public void removeById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("level_id", id);
        jdbcTemplate.update(removeByIdQuery, parameterSource);
    }

    public List<Level> findAllDeleted() {
        return jdbcTemplate.query(findAllDeletedQuery,
                BeanPropertyRowMapper.newInstance(Level.class));
    }


}