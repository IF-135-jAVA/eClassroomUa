//package com.softserve.betterlearningroom.repository;
//
//import com.softserve.betterlearningroom.entity.Materials;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//@PropertySource("classpath:materialsQuery.properties")
//public class MaterialsDAO {
//
//
//    private NamedParameterJdbcTemplate jdbcTemplate;
//
//@Autowired
//public MaterialsDAO(NamedParameterJdbcTemplate jdbcTemplate) {
//    this.jdbcTemplate = jdbcTemplate;
//}
//
//
//
//    @Value("$save")
//    private String saveQuery;
//
//    @Value("$update")
//    private String updateQuery;
//
//    @Value("$findAll")
//    private String findAllQuery;
//
//    @Value("$findById")
//    private String findByIdQuery;
//
//    @Value("$removeById")
//    private String removeByIdQuery;
//
//    @Value("$removeByTitle")
//    private String removeByTitleQuery;
//
//    @Value("$findByTitle")
//    private String findByTitleQuery;
//
//
//    @Value("$deleteAll")
//    private String deleteAllQuery;
//
//
//    public List<Materials> readAll() {
//        return jdbcTemplate.query(findAllQuery, BeanPropertyRowMapper.newInstance(Materials.class));
//    }
//
//    public  Materials readById(long id) {
//        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
//        return jdbcTemplate.queryForObject(findByIdQuery, parameterSource,
//                BeanPropertyRowMapper.newInstance(Materials.class));
//    }
//
//
//    public void create(Materials materials) {
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("text", materials.getText())
//                .addValue("comments", materials.getDueDate());
//
//        jdbcTemplate.update(saveQuery, parameterSource);
//    }
//
//    public void update(Materials updateMaterials) {
//        BeanPropertySqlParameterSource parameterSource =
//                new BeanPropertySqlParameterSource(updateMaterials);
//        jdbcTemplate.update(updateQuery, parameterSource);
//    }
//
//
//    public void delete(long id) {
//        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
//        jdbcTemplate.update(removeByIdQuery, parameterSource);
//
//    }
//
//}
//
//
//
//
//
//
//
//
//
