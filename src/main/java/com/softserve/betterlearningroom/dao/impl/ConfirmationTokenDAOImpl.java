package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.ConfirmationTokenDAO;
import com.softserve.betterlearningroom.dao.extractor.ConfirmationTokenRowMapper;
import com.softserve.betterlearningroom.entity.ConfirmationToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:db/confirmation-token/confirmation_token_queries.properties")
@Slf4j
public class ConfirmationTokenDAOImpl implements ConfirmationTokenDAO {

    private final NamedParameterJdbcTemplate template;
    private final ConfirmationTokenRowMapper mapper; // TODO: remove (?) this

    @Value("${find.by_id}")
    private String findById;

    @Value("${find.by_code}")
    private String findByCode;

    @Value("${save}")
    private String save;

    @Override
    public Optional<ConfirmationToken> findTokenById(Long id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        ConfirmationToken token = null;
        try {
            token = template.queryForObject(findById, param,
                    BeanPropertyRowMapper.newInstance(ConfirmationToken.class));
        } catch (DataAccessException ex) {
            log.error(String.format("Token with id - %d, not found.", id));
        }
        return Optional.ofNullable(token);
    }

    @Override
    public Optional<ConfirmationToken> findTokenByCode(String code) {
        SqlParameterSource param = new MapSqlParameterSource("code", code);
        ConfirmationToken token = null;
        try {
            token = template.queryForObject(findByCode, param,
                    BeanPropertyRowMapper.newInstance(ConfirmationToken.class));
        } catch (DataAccessException ex) {
            log.error(String.format("Token with code - %s, not found.", code));
        }
        return Optional.ofNullable(token);
    }

    @Override
    public ConfirmationToken save(ConfirmationToken token) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        params.addValue("code", token.getCode()).addValue("expires_at", token.getExpiresAt())
                .addValue("created_at", token.getCreatedAt()).addValue("user_id", token.getUser().getId());
        template.update(save, params , keyHolder);
        Long tokenId = 0L;
        if(keyHolder.getKey() != null) {
            tokenId = keyHolder.getKey().longValue();
        }
        token.setId(tokenId);
        return token;
    }

}
