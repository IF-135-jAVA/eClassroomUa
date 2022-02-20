package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.CriterionDAO;
import com.softserve.betterlearningroom.entity.Criterion;
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
import java.util.stream.Collectors;

@Repository
@PropertySource(value = "classpath:db/criterion/criterionQuery.properties")
public class CriterionDAOImpl implements CriterionDAO {

    private static final String CRITERION_ID = "criterion_id";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${criterion.save}")
    private String saveQuery;

    @Value("${criterion.update}")
    private String updateQuery;

    @Value("${criterion.findAll}")
    private String findAllQuery;

    @Value("${criterion.removeAllQuery}")
    private String removeAllQuery;

    @Value("${criterion.findById}")
    private String findByIdQuery;

    @Value("${criterion.removeById}")
    private String removeByIdQuery;

    @Value("${criterion.findByTitle}")
    private String findByTitleQuery;

    @Override
    public Criterion save(Criterion criterion) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource
                .addValue("material_id", criterion.getMaterialId())
                .addValue("title", criterion.getTitle())
                .addValue("description", criterion.getDescription());
        jdbcTemplate.update(saveQuery, parameterSource);
        return criterion;
    }

    @Override
    public Criterion update(Criterion criterion) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(criterion);
        jdbcTemplate.update(updateQuery, parameterSource);
        return criterion;
    }

    @Override
    public List<Criterion> findAll() {
        return jdbcTemplate.query(findAllQuery,
                BeanPropertyRowMapper.newInstance(Criterion.class));
    }

    @Override
    public Criterion findById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(CRITERION_ID, id);
        return jdbcTemplate.queryForObject(findByIdQuery, parameterSource,
                BeanPropertyRowMapper.newInstance(Criterion.class));

    }

    @Override
    public List<Criterion> findAllByMaterialId(Long materialId){
       return findAll().stream().filter(criterion ->criterion.getMaterialId().equals(materialId)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(CRITERION_ID, id);
        jdbcTemplate.update(removeByIdQuery, parameterSource);
    }
}
