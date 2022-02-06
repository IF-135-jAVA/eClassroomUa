package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenDAO {
    
    Optional<ConfirmationToken> findTokenById(Long id);
    Optional<ConfirmationToken> findTokenByCode(String code);
    ConfirmationToken save(ConfirmationToken token);

}
