package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.entity.Criterion;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@PropertySource(value = "classpath:db/criterion/criterionQuery.properties")
@AllArgsConstructor
public class CriterionDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${criterion.save}")
    private String saveQuery;

    @Value("${criterion.update}")
    private String updateQuery;

    @Value("${criterion.findAll}")
    private String findAllQuery;

    @Value("${criterion.findById}")
    private String findByIdQuery;

    @Value("${criterion.removeById}")
    private String removeByIdQuery;

    @Value("${criterion.findByTitle}")
    private String findByTitleQuery;

    public void save(Criterion criterion) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource
                .addValue("criterionid", criterion.getCriterionId())
                .addValue("materialid", criterion.getMaterialId())
                .addValue("title", criterion.getTitle())
                .addValue("description", criterion.getDescription());
        jdbcTemplate.update(saveQuery, parameterSource);
    }

    public void update(Criterion criterion) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(criterion);
        jdbcTemplate.update(updateQuery, parameterSource);
    }

    public List<Criterion> findAll() {
        return jdbcTemplate.query(findAllQuery,
                BeanPropertyRowMapper.newInstance(Criterion.class));


    }


    public Optional<Criterion> findById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("criterionid", id);
        return Optional.ofNullable(jdbcTemplate.queryForObject(findByIdQuery, parameterSource,
                    BeanPropertyRowMapper.newInstance(Criterion.class)));

    }


    public void removeById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("criterionid", id);
        jdbcTemplate.update(removeByIdQuery, parameterSource);
    }


    public Optional<List<Criterion>> findByTitle(String title) {
        return Optional.of(jdbcTemplate.query(findByTitleQuery,
                BeanPropertyRowMapper.newInstance(Criterion.class)));
    }

}
