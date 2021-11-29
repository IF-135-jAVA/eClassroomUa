package com.softserve.betterlearningroom.model;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends RepresentationModel<UserModel> {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private List<Classroom> classrooms;
	private boolean enabled;
	private Role role;

}
