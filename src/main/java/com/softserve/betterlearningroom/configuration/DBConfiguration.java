package com.softserve.betterlearningroom.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DBConfiguration {
	
	@Value("${datasource.driverClassName}")
	private String driverClassName;
	
	@Value("${datasource.url}")
	private String url;
	
	@Value("${datasource.username}")
	private String username;
	
	@Value("${datasource.password}")
	private String password;
	
	@Value("${datasource.maxActive}")
	private int maxActive;
	
	@Value("${datasource.minIdle}")
	private int minIdle;
	
	@Bean
	public DataSource postgresDataSource() {
        final HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaximumPoolSize(maxActive);
        dataSource.setMinimumIdle(minIdle);

        return dataSource;
    }

}
