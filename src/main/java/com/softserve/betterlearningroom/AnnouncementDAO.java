package com.softserve.betterlearningroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnnouncementDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AnnouncementDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public List<Announcement> readAll() {
        return jdbcTemplate.query("SELECT * FROM Announcement", new BeanPropertyRowMapper<>(Announcement.class));
    }

    public Announcement readById(int id) {
        return jdbcTemplate.query("SELECT * FROM Announcement WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Announcement.class))
                .stream().findAny().orElse(null);
    }

    public void create(Announcement announcement) {
       jdbcTemplate.update("INSERT INTO announcement VALUES(1, ?, ?)", announcement.getText(), announcement.getComments());

    }

    public void update(int id, Announcement updateAnnouncement) {
        jdbcTemplate.update("UPDATE announcement SET text=?, comments=? WHERE id=?", updateAnnouncement.getText(),
                updateAnnouncement.getComments(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM announcement WHERE id=?", id);
    }
}

