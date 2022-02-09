package com.softserve.betterlearningroom.dao.extractor;

import com.softserve.betterlearningroom.entity.ConfirmationToken;
import com.softserve.betterlearningroom.entity.User;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ConfirmationTokenRowMapper implements RowMapper<ConfirmationToken> {

    @Override
    public ConfirmationToken mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ConfirmationToken.builder()
                .id(rs.getLong("confirmation_tokens.id"))
                .code(rs.getString("code"))
                .expiresAt(rs.getTimestamp("expires_at").toLocalDateTime())
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .user(User.builder()
                        .id(rs.getLong("users.id"))
                        .email(rs.getString("email"))
                        .firstName(rs.getString("firstname"))
                        .lastName(rs.getString("lastname"))
                        .password(rs.getString("password"))
                        .confirmed(rs.getBoolean("confirmed"))
                        .enabled(rs.getBoolean("enabled"))
                        .provider(rs.getString("provider"))
                        .providerId(rs.getString("provider_id"))
                        .build())
                .build();
    }

}
