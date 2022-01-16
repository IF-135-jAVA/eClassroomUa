package com.softserve.betterlearningroom.security.oauth2;

import com.softserve.betterlearningroom.entity.Provider;
import com.softserve.betterlearningroom.exception.OAuth2AuthenticationProcessingException;
import com.softserve.betterlearningroom.security.oauth2.user.GoogleOAuth2User;
import com.softserve.betterlearningroom.security.oauth2.user.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(Provider.google.toString())) {
            return new GoogleOAuth2User(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException(
                    "Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}