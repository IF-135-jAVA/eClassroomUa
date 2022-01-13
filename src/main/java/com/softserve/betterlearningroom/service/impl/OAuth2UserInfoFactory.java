package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.entity.Provider;
import com.softserve.betterlearningroom.entity.oauth2.GoogleOAuth2User;
import com.softserve.betterlearningroom.entity.oauth2.OAuth2UserInfo;
import com.softserve.betterlearningroom.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(Provider.GOOGLE.toString())) {
            return new GoogleOAuth2User(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException(
                    "Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}