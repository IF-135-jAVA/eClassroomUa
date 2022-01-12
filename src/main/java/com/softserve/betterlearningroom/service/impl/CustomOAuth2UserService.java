package com.softserve.betterlearningroom.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

import com.softserve.betterlearningroom.dao.UserDAO;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.entity.oauth2.GoogleOAuth2User;

import lombok.AllArgsConstructor;

import static org.springframework.util.StringUtils.hasText;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;
    
	@Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user =  super.loadUser(userRequest);
        return new GoogleOAuth2User(user);
        
    }
	
	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, GoogleOAuth2User googleUser) {
	    
	    if(hasText(googleUser.getEmail())) {
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }
	    Optional<User> user = userDAO.findByEmail(googleUser.getEmail());
	    if(user.isEmpty()) {
	        User newUser = User.builder()
	                .email(googleUser.getEmail())
	                .firstName(googleUser.getName())
	                .lastName(googleUser.getLastName())
	                .enabled(true)
	                .password(passwordEncoder.encode("oauth_password"))
	                .provider("google")
	                .build();
	        userDAO.save(newUser);
	    }
	    return oAuth2User;
	}

}
