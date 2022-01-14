package com.softserve.betterlearningroom.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String login;
    private String password;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> grantedAuthorities;
    
    public static CustomUserDetails userToCustomUserDetails(User user) {
    	CustomUserDetails userDetails = new CustomUserDetails();
    	userDetails.id = user.getId();
    	userDetails.login = user.getEmail();
    	userDetails.password = user.getPassword();
    	userDetails.enabled = user.isEnabled();
    	return userDetails;
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
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
