package com.softserve.betterlearningroom.repository;

import com.softserve.betterlearningroom.entity.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:levelQuery.properties")
public class LevelDAO {

    private JdbcTemplate jdbcTemplate;

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


    public int save(Level level) {
        return jdbcTemplate.update(updateQuery,
                level.getId(), level.getTitle());
    }


    public int update(Level level) {
        return jdbcTemplate.update(updateQuery,
                level.getTitle(), level.getId());
    }

    public List<Level> findAll() {
        return jdbcTemplate.query(findAllQuery,
                BeanPropertyRowMapper.newInstance(Level.class));
    }

    public Optional<Level> findById(Integer id) {
        try {
            Level level = jdbcTemplate.queryForObject(findByIdQuery,
                    BeanPropertyRowMapper.newInstance(Level.class), id);

            return Optional.of(level);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }


    public int removeById(Integer id) {

        return jdbcTemplate.update(removeByIdQuery, id);
    }


    public Optional<List<Level>> findByTitle(String title) {

        return Optional.of(jdbcTemplate.query(findByTitleQuery,
                BeanPropertyRowMapper.newInstance(Level.class)));
    }

}
