package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.model.Level;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LevelRowMapper implements RowMapper<Level> {
    @Override
    public Level mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Level(rs.getLong("levelId"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("makr"));
    }
}
