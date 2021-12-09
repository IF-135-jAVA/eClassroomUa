package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Announcement;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnnouncementDAO {


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AnnouncementDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("SELECT id, text, comments FROM Announcement")
    private String getAll;

    @Value("SELECT id, text, comments FROM Announcement WHERE id=:id")
    private String getById;

    @Value("INSERT INTO Announcement (text, comments) VALUES (:text, :comments)")
    private String save;

    @Value("UPDATE Announcement SET text=:text, comments=:comments")
    private String edit;

    @Value("DELETE FROM announcement WHERE id=:id")
    private String remove;

    public List<Announcement> readAll() {
        return jdbcTemplate.query(getAll, BeanPropertyRowMapper.newInstance(Announcement.class));
    }

    public Announcement readById(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(getById, parameterSource,
                BeanPropertyRowMapper.newInstance(Announcement.class));
    }


    public void create(Announcement announcement) {
        BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(announcement);
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

