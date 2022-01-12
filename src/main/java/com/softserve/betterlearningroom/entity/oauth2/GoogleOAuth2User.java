package com.softserve.betterlearningroom.entity.oauth2;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GoogleOAuth2User implements OAuth2User {
	
	private OAuth2User oauth2User;

	@Override
	public Map<String, Object> getAttributes() {
		return oauth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return oauth2User.getAuthorities();
	}

	@Override
	public String getName() {
		return oauth2User.getAttribute("given_name");
	}
	
	public String getLastName() {
		return oauth2User.getAttribute("family_name");
	}
	
	public String getEmail() {
		return oauth2User.getAttribute("email");
	}

}
