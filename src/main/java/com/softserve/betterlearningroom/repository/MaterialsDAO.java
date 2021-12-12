package com.softserve.betterlearningroom.repository;

import com.softserve.betterlearningroom.entity.Materials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MaterialsDAO {

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


    @Value("$deleteAll")
    private String deleteAllQuery;


    public int save(Materials materials) {
        return jdbcTemplate.update(saveQuery,
                materials.getId(), materials.getTitle());
    }


    public int update(Materials materials) {
        return jdbcTemplate.update(updateQuery,
                materials.getTitle(), materials.getStartDate(), materials.getDueDate(), materials.getId());
    }

    public List<Materials> findAll() {
        return jdbcTemplate.query(findAllQuery, BeanPropertyRowMapper.newInstance(Materials.class));
    }

    public Optional<Materials> findById(Integer id) {
        try {
            Materials materials = jdbcTemplate.queryForObject(findByIdQuery,
                    BeanPropertyRowMapper.newInstance(Materials.class), id);

            return Optional.of(materials);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public Optional<Materials> findByTitle(String title) {
        Materials materials = jdbcTemplate.queryForObject(findByTitleQuery, BeanPropertyRowMapper.newInstance(Materials.class));
        return Optional.of(materials);
    }

    public int removeByTitle(String title) {

        return jdbcTemplate.update(removeByTitleQuery, title);
    }

    public int removeById(Integer id) {

        return jdbcTemplate.update(removeByIdQuery, id);
    }


//    public int deleteAll() {
//
//        return jdbcTemplate.update(deleteAllQuery);
//    }

}









