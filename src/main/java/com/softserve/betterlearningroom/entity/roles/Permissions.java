package com.softserve.betterlearningroom.entity.roles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permissions {
	
	USER_READ("user:read"),
	USER_WRITE("user:write"),
	CLASSROOM_READ("classroom:read"),
	CLASSROOM_WRITE("classroom:write"),
	TOPIC_READ("topic:read"),
	TOPIC_WRITE("topic:write"),
	ASSIGNMENT_READ("assignment:read"),
	ASSIGNMENT_WRITE("assignment:write"),
	USER_ASSIGNMENT_READ("userassignment:read"),
	USER_ASSIGNMENT_WRITE("userassignment:write"),
	COMMENT_READ("comment:read"),
	COMMENT_WRITE("comment:write"),
	CRITERION_READ("criterion:read"),
	CRITERION_WRITE("criterion:write"),
	LEVEL_READ("level:read"),
	LEVEL_WRITE("level:write"),
	ANNOUNCEMENT_READ("announcement:read"),
	ANNOUNCEMENT_WRITE("announcement:write");

	private final String permission;
}
