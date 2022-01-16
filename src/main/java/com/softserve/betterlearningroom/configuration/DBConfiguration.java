package com.softserve.betterlearningroom.configuration;

import com.softserve.betterlearningroom.dao.extractor.UserRowMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@Import(UserRowMapper.class)
public class DBConfiguration {

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
}
