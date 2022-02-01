package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.UserDAO;
import com.softserve.betterlearningroom.entity.Provider;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.entity.UserPrincipal;
import com.softserve.betterlearningroom.exception.OAuth2AuthenticationProcessingException;
import com.softserve.betterlearningroom.security.oauth2.OAuth2UserInfoFactory;
import com.softserve.betterlearningroom.security.oauth2.user.OAuth2UserInfo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Service
@AllArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private UserDAO userDAO;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);   
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * Performs an OAuth2 request then check if the user with retrieved email exists. If {@link UserDao} finds one then
     * <code>updateExistingUser()</code> is called. Otherwise <code>registerNewUser()</code>.
     * @param oAuth2UserRequest
     * @param oAuth2User
     * @exception OAuth2AuthenticationException when email not found from OAuth2 provider, or user is already registered with another provider.
     * @return {@link OAuth2User}
     */
    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());   
        if (!hasText(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }
        Optional<User> userOptional = userDAO.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            log.info("Registration is is " + oAuth2UserRequest.getClientRegistration().getRegistrationId());
            if(!user.getProvider().equals(Provider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()).name())) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            } 
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }
        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    /**
     * Registers a new {@link User} when not found one in the database.
     * @param oAuth2UserRequest current OAuth2 request
     * @param oAuth2UserInfo User data obtained from current OAuth2 provider
     * @return created {@link User}
     */
    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();

        user.setProvider(Provider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()).name());
        user.setProviderId(oAuth2UserInfo.getId());
        user.setFirstName(oAuth2UserInfo.getFirstName());
        user.setLastName(oAuth2UserInfo.getLastName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setPassword("oauth_password"); 
        log.info("Saving user " + user.toString());
        return userDAO.save(user);
    }

    /**
     * Updates an existing {@link User} when found one in the database.
     * @param existingUser {@link User}, that is already stored in the database with current OAuth2 provider
     * @param oAuth2UserInfo    User data obtained from current OAuth2 provider
     * @return updated {@link User}
     */
    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setFirstName(oAuth2UserInfo.getFirstName());
        existingUser.setLastName(oAuth2UserInfo.getLastName());
        log.info("Updated user " + existingUser.toString());
        return userDAO.update(existingUser);
    }
}
