package com.softserve.betterlearningroom.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserRequest {
	
	private String firstName;

	private String lastName; 
	
	private String password;
	
	private String email;

	private boolean enabled;

}
