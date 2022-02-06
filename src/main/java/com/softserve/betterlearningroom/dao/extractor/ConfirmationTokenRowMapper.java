package com.softserve.betterlearningroom.dao.extractor;

import com.softserve.betterlearningroom.entity.ConfirmationToken;
import com.softserve.betterlearningroom.entity.User;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfirmationTokenRowMapper implements RowMapper<ConfirmationToken> {

    @Override
    public ConfirmationToken mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ConfirmationToken.builder()
                .id(rs.getLong("id"))
                .code(rs.getString("code"))
                .expiresAt(rs.getTimestamp("expires_at").toLocalDateTime())
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .user((User) rs.getObject("user_id"))
                .build();
    }

}
