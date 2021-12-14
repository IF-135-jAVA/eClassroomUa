package com.softserve.betterlearningroom.repository;

import com.softserve.betterlearningroom.entity.Topic;
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
@PropertySource("classpath:topicQuery.properties")
public class TopicDAO {


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


    public int save(Topic topic) {
        return jdbcTemplate.update(saveQuery,
                topic.getId(), topic.getTitle(), topic.getClassroomId());
    }


    public int update(Topic topic) {
        return jdbcTemplate.update(updateQuery,
                topic.getTitle(), topic.getId());
    }


    public List<Topic> findAll() {
        return jdbcTemplate.query(findAllQuery, BeanPropertyRowMapper.newInstance(Topic.class));
    }

    public Optional<Topic> findById(Integer id) {
        try {
            Topic topic = jdbcTemplate.queryForObject(findByIdQuery,
                    BeanPropertyRowMapper.newInstance(Topic.class), id);

            return Optional.of(topic);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }


    public int removeById(Integer id) {

        return jdbcTemplate.update(removeByIdQuery, id);
    }


    public Optional<List<Topic>> findByTitle(String title) {

        return Optional.of(jdbcTemplate.query(findByTitleQuery, BeanPropertyRowMapper.newInstance(Topic.class)));
    }


}
