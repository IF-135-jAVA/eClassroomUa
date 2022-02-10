package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.entity.ConfirmationToken;
import com.softserve.betterlearningroom.service.ConfirmationTokenService;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{

    @Override
    @Cacheable(cacheNames = "confirmation_token", key = "#id")
    public ConfirmationToken findTokenById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Cacheable(cacheNames = "confirmation_token", key = "#code")
    public ConfirmationToken findTokenByCode(String code) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @CachePut(cacheNames = "confirmation_token", key = "#token.id")
    public ConfirmationToken save(ConfirmationToken token) {
        // TODO Auto-generated method stub
        return null;
    }

}
