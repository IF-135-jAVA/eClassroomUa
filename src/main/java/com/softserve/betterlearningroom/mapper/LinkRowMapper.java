package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.model.Link;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LinkRowMapper implements RowMapper<Link> {
    @Override
    public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Link(rs.getLong("linkId"),
                rs.getString("url"),
                rs.getString("linktext"));
    }
}
