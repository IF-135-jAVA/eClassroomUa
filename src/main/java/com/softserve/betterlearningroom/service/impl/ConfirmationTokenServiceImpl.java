package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.entity.ConfirmationToken;
import com.softserve.betterlearningroom.service.ConfirmationTokenService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{

    @Override
    @Cacheable(cacheNames = "confirmation_tokens", key = "#code")
    public ConfirmationToken findTokenByCode(String code) {
        return null;
    }

    @Override
    @CachePut(cacheNames = "confirmation_tokens", key = "#token.code")
    public ConfirmationToken save(ConfirmationToken token) {
        return token;
    }
    
    @Override
    @CacheEvict(cacheNames = "confirmation_tokens", key = "#code")
    public void delete(String code) {}

}
