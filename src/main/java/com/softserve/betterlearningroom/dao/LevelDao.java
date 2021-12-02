package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.mapper.LevelRowMapper;
import com.softserve.betterlearningroom.model.Criterion;
import com.softserve.betterlearningroom.model.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class LevelDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Level> getAllLevels(Long criterionId) {
        return jdbcTemplate.query("SELECT * FROM levels where criterionid=?", new Object[] { criterionId }, new LevelRowMapper());
    }

    public List<Level> getAllLevels(Criterion criterion) {
        return jdbcTemplate.query("SELECT * FROM levels where criterionid=?", new Object[] { criterion.getId() }, new LevelRowMapper());
    }

    public boolean addLevel(Level level, Long criterionId) {
        return jdbcTemplate.update("INSERT INTO levels (title, description, makr, criterionid) VALUES (?, ?, ?, ?)", level.getTitle(), level.getDescription(), level.getMark(), criterionId) == 1;
    }

    public boolean addLevel(Level level, Criterion criterion) {
        return jdbcTemplate.update("INSERT INTO levels (title, description, makr, criterionid) VALUES (?, ?, ?, ?)", level.getTitle(), level.getDescription(), level.getMark(), criterion.getId()) == 1;
    }

    public boolean updateLevel(Level level) {
        return jdbcTemplate.update("UPDATE levels SET title=?, description=?, makr=? WHERE criterionid=?", level.getTitle(), level.getDescription(), level.getMark(), level.getId()) == 1;
    }

    public boolean removeLevel(Long levelId) {
        return jdbcTemplate.update("DELETE FROM levels WHERE levelid=?", levelId) == 1;
    }

    public boolean removeLevel(Level level) {
        return jdbcTemplate.update("DELETE FROM levels WHERE levelid=?", level.getId()) == 1;
    }

}