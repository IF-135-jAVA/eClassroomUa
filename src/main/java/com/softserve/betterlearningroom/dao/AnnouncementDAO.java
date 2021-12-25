package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Announcement;
import lombok.AllArgsConstructor;
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
@PropertySource(value = "classpath:/db/announcements/announcement_queries.properties")
@AllArgsConstructor
public class AnnouncementDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Value("${find.all.announcements}")
    private String getAll;

    @Value("${find.by.id.announcement}")
    private String getById;

    @Value("${save.announcement}")
    private String save;

    @Value("${update.announcement}")
    private String edit;

    @Value("${remove.announcement}")
    private String remove;

    public List<Announcement> readAll() {
        return namedParameterJdbcTemplate.query(getAll, BeanPropertyRowMapper.newInstance(Announcement.class));
    }

    public Announcement readById(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(getById, parameterSource,
                BeanPropertyRowMapper.newInstance(Announcement.class));
    }


    public void create(Announcement announcement) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("course_id", announcement.getCourseId())
                .addValue("text", announcement.getText());


        namedParameterJdbcTemplate.update(save, parameterSource);
    }

    public void update(Announcement updateAnnouncement) {
        BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(updateAnnouncement);
        namedParameterJdbcTemplate.update(edit, parameterSource);
    }


    public void delete(long id) {
        // TODO: 25.12.2021 we don't delete from DB, we just archive it
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(remove, parameterSource);

    }
}











