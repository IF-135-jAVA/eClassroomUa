package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Announcement;
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

@Repository
@PropertySource(value = "classpath:/announcement_queries.properties")
public class AnnouncementDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AnnouncementDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${findAll.Announcements}")
    private String getAll;

    @Value("${findById.Announcement}")
    private String getById;

    @Value("${save.Announcement}")
    private String save;

    @Value("${update.Announcement}")
    private String edit;

    @Value("${remove.Announcement}")
    private String remove;

    public List<Announcement> readAll() {
        return jdbcTemplate.query(getAll, BeanPropertyRowMapper.newInstance(Announcement.class));
    }

    public List<Announcement> readById(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.query(getById, parameterSource, BeanPropertyRowMapper.newInstance(Announcement.class));
    }

    public void create(Announcement announcement) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("courseId", announcement.getCourseId())
                .addValue("text", announcement.getText());
        jdbcTemplate.update(save, parameterSource);
    }

    public void update(Announcement updateAnnouncement) {
        BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(updateAnnouncement);
        jdbcTemplate.update(edit, parameterSource);
    }

    public void delete(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(remove, parameterSource);
    }
}











