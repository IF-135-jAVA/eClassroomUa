package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.mapper.CriterionRowMapper;
import com.softserve.betterlearningroom.model.Criterion;
import com.softserve.betterlearningroom.model.Level;
import com.softserve.betterlearningroom.model.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class CriterionDao {

    private JdbcTemplate jdbcTemplate;

    private LevelDao levelDao;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Criterion> getAllCriterions(Long materialId) {
        return jdbcTemplate.query("SELECT * FROM criterions where materialid=?", new Object[] { materialId }, new CriterionRowMapper());
    }

    public List<Criterion> getAllCriterions(Material material) {
        return jdbcTemplate.query("SELECT * FROM criterions where materialid=?", new Object[] { material.getId() }, new CriterionRowMapper());
    }

    public boolean addCriterion(Criterion criterion, Material material) {
        for (Level level : criterion.getLevels()) {
            levelDao.addLevel(level, criterion);
        }
        return jdbcTemplate.update("INSERT INTO criterions (title, description, materialid) VALUES (?, ?, ?)", criterion.getTitle(), criterion.getDescription(), material.getId()) == 1;
    }

    public boolean addCriterion(Criterion criterion, Long materialId) {
        for (Level level : criterion.getLevels()) {
            levelDao.addLevel(level, criterion);
        }
        return jdbcTemplate.update("INSERT INTO criterions (title, description, materialid) VALUES (?, ?, ?)", criterion.getTitle(), criterion.getDescription(), materialId) == 1;
    }

    public boolean updateCriterion(Criterion criterion) {
        for (Level level : criterion.getLevels()) {
            levelDao.updateLevel(level);
        }
        return jdbcTemplate.update("UPDATE criterions SET title=?, description=? WHERE criterionid=?", criterion.getTitle(), criterion.getDescription(), criterion.getId()) == 1;
    }

    public boolean removeCriterion(Long criterionId) {
        for (Level level : levelDao.getAllLevels(criterionId)) {
            levelDao.removeLevel(level);
        }
        return jdbcTemplate.update("DELETE FROM criterions WHERE criterionid=?", criterionId) == 1;
    }

    public boolean removeCriterion(Criterion criterion) {
        for (Level level : criterion.getLevels()) {
            levelDao.removeLevel(level);
        }
        return jdbcTemplate.update("DELETE FROM criterions WHERE criterionid=?", criterion.getId()) == 1;
    }

}
