package com.softserve.betterlearningroom.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="firstname")
	@NotBlank(message = "Firstame must not be empty")
	private String firstName;
	
	@Column(name="lastname")
	@NotBlank(message = "Lastname must not be empty")
	private String lastName; 
	
	//TODO: Check pwd
	@Column(name="password")
	@NotBlank(message = "Password must not be empty")
	private String password;
	
	@Column(name="email")
	@NotBlank(message = "Email must not be empty")
    @Email(message = "Email must be a valid email address")
	private String email;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@JoinColumn(name="classrooms")
	@ManyToMany
	private List<Classroom> classrooms;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return email;
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
