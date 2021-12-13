package com.softserve.betterlearningroom.entity.request;

import lombok.Data;

@Data
public class AuthRequest {
	private String login;
	private String password;
}
