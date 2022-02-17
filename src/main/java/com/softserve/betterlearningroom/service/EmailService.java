package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.entity.ConfirmationToken;
import com.softserve.betterlearningroom.entity.User;

public interface EmailService {
    
    void sendEmail(User user, ConfirmationToken token);

}
