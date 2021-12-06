package com.softserve.betterlearningroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnnouncementDAO {


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AnnouncementDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Announcement> readAll() {
        return jdbcTemplate.query("SELECT id, text, comments FROM Announcement",
                BeanPropertyRowMapper.newInstance(Announcement.class));
    }


    public Announcement readById(long id) {
        String sql = "SELECT id, text, comments FROM Announcement WHERE id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(sql, parameterSource,
                BeanPropertyRowMapper.newInstance(Announcement.class));
    }


    public void create(Announcement announcement) {
        String sql = "INSERT INTO Announcement (text, comments) VALUES (:text, :comments)";
        BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(announcement);
        jdbcTemplate.update(sql, parameterSource);
    }

    public void update(long id, Announcement updateAnnouncement) {
        String sql = "UPDATE Announcement SET text=:text, comments=:comments";
        BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(updateAnnouncement);
        jdbcTemplate.update(sql, parameterSource);
    }


    public void delete(long id) {
        String sql = "DELETE FROM announcement WHERE id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, parameterSource);

    }
}

