package com.softserve.betterlearningroom.service;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.softserve.betterlearningroom.controller.UserController;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.model.UserModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel>{

	
	public UserModelAssembler(Class<?> controllerClass, Class<UserModel> resourceType) {
		super(UserController.class, UserModel.class);
	}

	@Override
	public UserModel toModel(User user) {
		UserModel userModel = instantiateModel(user);
		userModel.setId(user.getId());
		userModel.setClassrooms(user.getClassrooms());
		userModel.setEmail(user.getEmail());
		userModel.setEnabled(user.isAccountNonExpired());
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		
		userModel.add(linkTo(
				methodOn(UserController.class)
				.findUserById(user.getId()))
				.withSelfRel());
		return userModel;
	}
	
	@Override
	public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> users) {
		CollectionModel<UserModel> usersModel = super.toCollectionModel(users);
		usersModel.add(linkTo(
				methodOn(UserController.class)
				.findAllUsers())
				.withSelfRel());
		return usersModel;
	}

}
