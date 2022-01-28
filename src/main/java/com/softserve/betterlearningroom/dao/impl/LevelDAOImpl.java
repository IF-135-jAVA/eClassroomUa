package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.LevelDAO;
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
public class LevelDAOImpl implements LevelDAO {

    private static final String CRITERION_ID = "criterion_id";

    private static final String LEVEL_ID = "level_id";

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

    @Value("${level.findByCriterionId}")
    private String findByCriterionIdQuery;

    @Override
    public Level save(Level level) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource
                .addValue(LEVEL_ID, level.getLevelId())
                .addValue("title", level.getTitle())
                .addValue("description", level.getDescription())
                .addValue(CRITERION_ID, level.getCriterionId())
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
    public List<Level> findAllByCriterionId(Long criterionId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(CRITERION_ID, criterionId);
        return jdbcTemplate.query(findByCriterionIdQuery, parameterSource,
                BeanPropertyRowMapper.newInstance(Level.class));

    }

    @Override
    public Level findById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(LEVEL_ID, id);
        return jdbcTemplate.queryForObject(findByIdQuery, parameterSource,
                BeanPropertyRowMapper.newInstance(Level.class));
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(LEVEL_ID, id);
        jdbcTemplate.update(removeByIdQuery, parameterSource);
    }

    public List<Level> findAllDeleted() {
        return jdbcTemplate.query(findAllDeletedQuery,
                BeanPropertyRowMapper.newInstance(Level.class));
    }


}
