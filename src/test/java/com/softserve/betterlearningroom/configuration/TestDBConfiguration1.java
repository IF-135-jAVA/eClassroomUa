package com.softserve.betterlearningroom.configuration;

import com.softserve.betterlearningroom.dao.extractor.AnnouncementRowMapper;
import com.softserve.betterlearningroom.dao.extractor.CommentRowMapper;
import com.softserve.betterlearningroom.dao.extractor.UserRowMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Import({UserRowMapper.class, CommentRowMapper.class, AnnouncementRowMapper.class})
public class TestDBConfiguration1 {
	
	@Bean
	public DataSource postgresDataSource() {
        final DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/db/users/schema.sql")
                .addScript("classpath:/db/users/test-data.sql")
				.addScript("classpath:/db/comments/schema.sql")
				.addScript("classpath:/db/comments/test-data.sql")
				.addScript("classpath:/db/announcements/schema.sql")
				.addScript("classpath:/db/announcements/test-data.sql")
				.build();

        return dataSource;
    }
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(postgresDataSource());
	}

}
