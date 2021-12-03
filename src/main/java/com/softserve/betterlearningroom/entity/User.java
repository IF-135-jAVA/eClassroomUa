package com.softserve.betterlearningroom.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class User{

	private int id;
	
	private String firstName;

	private String lastName; 
	
	private String password;
	
	private String email;

	private boolean enabled;

}
