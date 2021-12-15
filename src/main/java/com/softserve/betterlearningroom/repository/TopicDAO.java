package com.softserve.betterlearningroom.repository;

import com.softserve.betterlearningroom.entity.Topic;
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
@PropertySource(value = "classpath:/topicQuery.properties")
public class TopicDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${topic.save}")
    private String saveQuery;

    @Value("${topic.update}")
    private String updateQuery;

    @Value("${topic.findAll}")
    private String findAllQuery;

    @Value("${topic.findById}")
    private String findByIdQuery;

    @Value("${topic.removeById}")
    private String removeByIdQuery;

    @Value("${topic.findByTitle}")
    private String findByTitleQuery;

    public void save(Topic topic) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource
                .addValue("classroomId", topic.getClassroomId())
                .addValue("title", topic.getTitle());
        jdbcTemplate.update(saveQuery, parameterSource);
    }

    public void update(Topic topic) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(topic);
        jdbcTemplate.update(updateQuery, parameterSource);
    }

    public List<Topic> findAll() {
        return jdbcTemplate.query(findAllQuery,
                BeanPropertyRowMapper.newInstance(Topic.class));
    }


    public Optional<Topic> findById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("topic_id", id);
        return Optional.ofNullable(jdbcTemplate.queryForObject(findByIdQuery, parameterSource,
                BeanPropertyRowMapper.newInstance(Topic.class)));

    }


    public void removeById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("topic_id", id);
        jdbcTemplate.update(removeByIdQuery, parameterSource);
    }


    public Optional<List<Topic>> findByTitle(String title) {
        return Optional.of(jdbcTemplate.query(findByTitleQuery, BeanPropertyRowMapper.newInstance(Topic.class)));
    }

}
