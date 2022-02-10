package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.entity.ConfirmationToken;

public interface ConfirmationTokenService {

    ConfirmationToken findTokenById(Long id);
    ConfirmationToken findTokenByCode(String code);
    ConfirmationToken save(ConfirmationToken token);
}
