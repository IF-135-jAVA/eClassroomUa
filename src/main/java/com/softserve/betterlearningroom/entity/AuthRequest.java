package com.softserve.betterlearningroom.entity;

import lombok.Data;

@Data
public class AuthRequest {	
	private String login;
    private String password;
}
