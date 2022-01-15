package com.softserve.betterlearningroom.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@TestConfiguration
public class TestConfig {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    @Bean
//    public MaterialDao materialDao() {
//        return new MaterialDao(namedParameterJdbcTemplate);
//    }
//
//    @Bean
//    public TopicDAO topicDAO() {
//        return new TopicDAO(namedParameterJdbcTemplate);
//    }
//
//    @Bean
//    public ClassroomDAO classroomDAO(){
//        return new ClassroomDAO(namedParameterJdbcTemplate);
//    }

}