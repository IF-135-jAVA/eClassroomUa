package com.softserve.betterlearningroom.entity.oauth2;

import java.util.Map;

public class GoogleOAuth2User extends OAuth2UserInfo {

    public GoogleOAuth2User(Map<String, Object> attributes) {
        super(attributes);
    }
    
    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getFirstName() {
        return (String) attributes.get("given_name");
    }

    @Override
    public String getLastName() {
        return (String) attributes.get("family_name");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }
}
