package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.entity.ConfirmationToken;

public interface ConfirmationTokenService {
    
    /**
     * Finds {@link ConfirmationToken} from the cache by the code.
     * @param code Special UUID <i>String</i>.
     * @return {@link ConfirmationToken}
     */
    ConfirmationToken findTokenByCode(String code);
    
    /**
     * Saves {@link ConfirmationToken} to the cache.
     * @param token {@link ConfirmationToken}.
     * @return {@link ConfirmationToken}.
     */
    ConfirmationToken save(ConfirmationToken token);
    
    /**
     * Deletes {@link ConfirmationToken} from the cache by the code.
     * @param code Special UUID <i>String</i>.
     */
    void delete(String code);
}
