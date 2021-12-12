package com.softserve.betterlearningroom.repository;

import com.softserve.betterlearningroom.entity.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CriterionDAO {

    @Autowired
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


    public int save(Criterion criterion) {
        return jdbcTemplate.update(saveQuery,
                criterion.getId(), criterion.getTitle());
    }


    public int update(Criterion criterion) {
        return jdbcTemplate.update(updateQuery,
                criterion.getTitle(), criterion.getId());
    }


    public List<Criterion> findAll() {
        return jdbcTemplate.query(findAllQuery,
                BeanPropertyRowMapper.newInstance(Criterion.class));
    }


    public Optional<Criterion> findById(Integer id) {
        try {
            Criterion criterion = jdbcTemplate.queryForObject(findByIdQuery,
                    BeanPropertyRowMapper.newInstance(Criterion.class), id);

            return Optional.of(criterion);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }


    public int removeById(Integer id) {

        return jdbcTemplate.update(removeByIdQuery, id);
    }


    public Optional<List<Criterion>> findByTitle(String title) {

        return Optional.of(jdbcTemplate.query(findByTitleQuery, BeanPropertyRowMapper.newInstance(Criterion.class)));
    }

}
