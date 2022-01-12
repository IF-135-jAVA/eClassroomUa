package com.softserve.betterlearningroom.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@TestConfiguration
public class TestDBConfiguration {
	
	@Bean
	public DataSource postgresDataSource() {
        final DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/db/criterion/schema.sql")
                .addScript("classpath:/db/criterion/test-data.sql")
                .build();

        return dataSource;
    }
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(postgresDataSource());
	}

}
