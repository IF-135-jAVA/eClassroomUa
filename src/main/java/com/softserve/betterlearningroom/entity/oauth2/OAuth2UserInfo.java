package com.softserve.betterlearningroom.entity.oauth2;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public abstract class OAuth2UserInfo {
    
    protected Map<String, Object> attributes;

    public abstract String getId();

    public abstract String getFirstName();
    
    public abstract String getLastName();

    public abstract String getEmail();

}
