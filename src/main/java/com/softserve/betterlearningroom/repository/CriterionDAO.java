package com.softserve.betterlearningroom.repository;

import com.softserve.betterlearningroom.entity.Criterion;
import lombok.RequiredArgsConstructor;
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
import java.util.Optional;

@Repository

@RequiredArgsConstructor
@PropertySource("classpath:criterionQuery.properties")
public class CriterionDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CriterionDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("$save")
    private String saveQuery;

    @Value("$update")
    private String updateQuery;

    @Value("$findAll")
    private String findAllQuery;

    @Value("$findById")
    private String findByIdQuery;

    @Value("$removeById")
    private String removeByIdQuery;

    @Value("$removeByTitle")
    private String removeByTitleQuery;

    @Value("$findByTitle")
    private String findByTitleQuery;

    public void save(Criterion criterion) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("title", criterion.getTitle())
                .addValue("description", criterion.getDescription());
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
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return Optional.ofNullable(jdbcTemplate.queryForObject(findByIdQuery, parameterSource,
                    BeanPropertyRowMapper.newInstance(Criterion.class)));

    }


    public void removeById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(removeByIdQuery, parameterSource);
    }


    public Optional<List<Criterion>> findByTitle(String title) {
        return Optional.of(jdbcTemplate.query(findByTitleQuery, BeanPropertyRowMapper.newInstance(Criterion.class)));
    }

}
