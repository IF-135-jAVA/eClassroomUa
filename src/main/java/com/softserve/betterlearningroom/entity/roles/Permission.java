package com.softserve.betterlearningroom.entity.roles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
	
	USER_READ("user:read"),
	USER_WRITE("user:write"),
	CLASSROOM_READ("classroom:read"),
	CLASSROOM_WRITE("classroom:write"),
	TOPIC_READ("topic:read"),
	TOPIC_WRITE("topic:write"),
	ASIGNMENT_READ("asignment:read"),
	ASIGNMENT_WRITE("asignment:wite"),
	USERASIGNMENT_READ("userasignment:read"),
	USERASIGNMENT_WRITE("userasignment:write"),
	COMMENT_READ("comment:read"),
	COMMENT_WRITE("comment:write"),
	CRITERION_READ("criterion:read"),
	CRITERION_WRITE("criterion:write"),
	LEVEL_READ("level:read"),
	LEVEL_WRITE("level:write");
	
	private final String permission;
}
