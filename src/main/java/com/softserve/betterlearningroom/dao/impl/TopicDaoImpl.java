package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.TopicDao;
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
import java.util.stream.Collectors;

@Repository
@PropertySource(value = "classpath:/db/topic/topicQuery.properties")
public class TopicDaoImpl implements TopicDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${topic.save}")
    private String saveQuery;

    @Value("${topic.update}")
    private String updateQuery;

    @Value("${topic.findAll}")
    private String findAllQuery;

    @Value("${topic.findAllDeleted}")
    private String findAllDeletedQuery;

    @Value("${topic.findById}")
    private String findByIdQuery;

    @Value("${topic.removeById}")
    private String removeByIdQuery;

    @Value("${topic.findByTitle}")
    private String findByTitleQuery;

    @Override
    public Topic save(Topic topic) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource
                .addValue("classroomId", topic.getClassroomId())
                .addValue("title", topic.getTitle());
        jdbcTemplate.update(saveQuery, parameterSource);
        return topic;
    }

    @Override
    public Topic update(Topic topic) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(topic);
        jdbcTemplate.update(updateQuery, parameterSource);
        return topic;
    }

    @Override
    public List<Topic> findAll() {
        return jdbcTemplate.query(findAllQuery,
                BeanPropertyRowMapper.newInstance(Topic.class));
    }

    @Override
    public List<Topic> findAllByClassroomId(Long classroomId) {
        return findAll().stream().filter(topic -> topic.getClassroomId().equals(classroomId)).collect(Collectors.toList());
    }

    @Override
    public Topic findById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("topic_id", id);
        return jdbcTemplate.queryForObject(findByIdQuery, parameterSource,
                BeanPropertyRowMapper.newInstance(Topic.class));

    }

    @Override
    public void removeById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("topic_id", id);
        jdbcTemplate.update(removeByIdQuery, parameterSource);
    }

    public List<Topic> findAllDeleted() {
        return jdbcTemplate.query(findAllDeletedQuery,
                BeanPropertyRowMapper.newInstance(Topic.class));
    }

    public Optional<List<Topic>> findByTitle(String title) {
        return Optional.of(jdbcTemplate.query(findByTitleQuery, BeanPropertyRowMapper.newInstance(Topic.class)));
    }

}
