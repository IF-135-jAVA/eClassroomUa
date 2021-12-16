package com.softserve.betterlearningroom.repository;

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
import java.util.Optional;

@Repository
@PropertySource(value = "classpath:levelQuery.properties")
public class LevelDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${level.save}")
    private String saveQuery;

    @Value("${level.update}")
    private String updateQuery;

    @Value("${level.findAll}")
    private String findAllQuery;

    @Value("${level.findById}")
    private String findByIdQuery;

    @Value("${level.removeById}")
    private String removeByIdQuery;


    public void save(Level level) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource
                .addValue("levelId", level.getLevelid())
                .addValue("title", level.getTitle())
                .addValue("description", level.getDescription())
                .addValue("criterionId", level.getCriterionid())
                .addValue("mark", level.getMark());
        jdbcTemplate.update(saveQuery, parameterSource);
    }

    public void update(Level level) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(level);
        jdbcTemplate.update(updateQuery, parameterSource);
    }

    public List<Level> findAll() {
        return jdbcTemplate.query(findAllQuery,
                BeanPropertyRowMapper.newInstance(Level.class));
    }


    public Optional<Level> findById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("levelId", id);
        return Optional.ofNullable(jdbcTemplate.queryForObject(findByIdQuery, parameterSource,
                BeanPropertyRowMapper.newInstance(Level.class)));
    }

        public void removeById (Integer id){
            SqlParameterSource parameterSource = new MapSqlParameterSource("levelId", id);
            jdbcTemplate.update(removeByIdQuery, parameterSource);
        }

}