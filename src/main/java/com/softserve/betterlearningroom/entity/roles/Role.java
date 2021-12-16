package com.softserve.betterlearningroom.entity.roles;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.softserve.betterlearningroom.entity.roles.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {
	
	TEACHER(Arrays.asList(ASIGNMENT_READ, ASIGNMENT_WRITE, USER_READ, USER_WRITE, CLASSROOM_READ,
			CLASSROOM_WRITE, TOPIC_READ, TOPIC_WRITE, USERASIGNMENT_READ, USERASIGNMENT_WRITE,
			COMMENT_READ, COMMENT_WRITE, CRITERION_READ, CRITERION_WRITE, LEVEL_READ, LEVEL_WRITE).stream().collect(Collectors.toSet())),
	STUDENT(Arrays.asList(ASIGNMENT_READ, ASIGNMENT_WRITE, USER_READ, USER_WRITE, CLASSROOM_READ,
			TOPIC_READ, USERASIGNMENT_READ, USERASIGNMENT_WRITE, COMMENT_READ, COMMENT_WRITE,
			CRITERION_READ, LEVEL_READ).stream().collect(Collectors.toSet()));

	private final Set<Permission> permissions;
	
	 public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		 
		 Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
				 .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				 .collect(Collectors.toSet());
		 permissions.add(new SimpleGrantedAuthority(this.name()));
		 return permissions;
	 }
	
}
