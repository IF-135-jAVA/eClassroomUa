package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{

	private int id;
	
	private String firstName;

	private String lastName; 
	
	private String password;
	
	private String email;

	private boolean enabled;

}
