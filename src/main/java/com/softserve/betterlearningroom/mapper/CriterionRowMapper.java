package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dao.LevelDao;
import com.softserve.betterlearningroom.model.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CriterionRowMapper implements RowMapper<Criterion> {

    @Autowired
    private LevelDao levelDao;

    @Override
    public Criterion mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Criterion(rs.getLong("criterionId"),
                rs.getString("title"),
                rs.getString("description"),
                levelDao.getAllLevels(rs.getLong("criterionId")));
    }
}
