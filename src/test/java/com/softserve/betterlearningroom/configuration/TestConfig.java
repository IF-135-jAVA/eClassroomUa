package com.softserve.betterlearningroom.configuration;

import javax.naming.Name;
import javax.sql.DataSource;

import com.softserve.betterlearningroom.dao.ClassroomDAO;
import com.softserve.betterlearningroom.dao.MaterialDao;
import com.softserve.betterlearningroom.dao.impl.TopicDAO;
import com.softserve.betterlearningroom.entity.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@TestConfiguration
public class TestConfig {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Bean
    public MaterialDao materialDao() {
        return new MaterialDao(namedParameterJdbcTemplate);
    }

    @Bean
    public TopicDAO topicDAO() {
        return new TopicDAO(namedParameterJdbcTemplate);
    }

    @Bean
    public ClassroomDAO classroomDAO(){
        return new ClassroomDAO(namedParameterJdbcTemplate);
    }

    @Bean
    public DataSource postgresDataSource() {
        final HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl("jdbc:postgresql://dtapi.if.ua:5432/javadog");
        dataSource.setUsername("javadog");
        dataSource.setPassword("5rav_Pe5");
        dataSource.setMaximumPoolSize(20);
        dataSource.setMinimumIdle(10);

        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(postgresDataSource());
    }

}