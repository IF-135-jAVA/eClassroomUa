package com.softserve.betterlearningroom.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class UserPrincipal implements OAuth2User, UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String login;
    private String password;
    private boolean enabled;
    private boolean confirmed;
    private Collection<? extends GrantedAuthority> grantedAuthorities;
    private Map<String, Object> attributes;
    
    public static UserPrincipal create(User user) {
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.id = user.getId();
        userPrincipal.login = user.getEmail();
        userPrincipal.password = user.getPassword();
        userPrincipal.enabled = user.isEnabled();
        userPrincipal.confirmed = user.isConfirmed();
        return userPrincipal;
    }
    
    public static UserPrincipal create(User user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }
    
    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return confirmed;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getName() {
        return login;
    }
    
    @Override
    public Map<String, Object>  getAttributes() {
        return attributes;
    }
    
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
