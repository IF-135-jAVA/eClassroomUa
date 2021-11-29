package com.softserve.betterlearningroom.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;
import com.softserve.betterlearningroom.model.UserModel;
import com.softserve.betterlearningroom.service.UserModelAssembler;
import com.softserve.betterlearningroom.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
	
	private final UserService userService;
	private final UserModelAssembler userAssembler;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<UserModel> findUserById(@PathVariable @Min(value = 1, message = "ID must be greater then 0.") int id){
		User user = userService.findById(id);	
		return ResponseEntity.ok().body(userAssembler.toModel(user));
	}
	
	@GetMapping
	public ResponseEntity<CollectionModel<UserModel>> findAllUsers(){
		List<User> users = userService.findAll();
		return new ResponseEntity<>(
				userAssembler.toCollectionModel(users),
				HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Classroom>> getAllClasrooms(@PathVariable @Min(value = 1, message = "ID must be greater then 0.") int id){
		List<Classroom> classrooms = userService.getClassrooms(id);
		return  new ResponseEntity<>(
				classrooms,
				HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody @Valid User user) throws UserAlreadyExistsException{
		User addedUser = userService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(addedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/{id}/classroom")
	public ResponseEntity<UserModel> addClassroom(@PathVariable @Min(value = 1, message = "ID must be greater then 0.") int id,
			@RequestBody @Valid Classroom classroom) {
		User user = userService.addClassroom(id, classroom);
		return ResponseEntity.ok().body(userAssembler.toModel(user));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserModel> updateUser(@PathVariable @Min(value = 1, message = "ID must be greater then 0.") int id,
			@RequestBody @Valid User user) {
		User updatedUser = userService.updateUser(user, id);
		return ResponseEntity.ok().body(userAssembler.toModel(updatedUser));
	}
	
	@PutMapping("/disable/{id}")
	public ResponseEntity<?> disableUser(@PathVariable @Min(value = 1, message = "ID must be greater then 0.") int id) {
		userService.disableUser(id);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteDisadledUsers(){
		userService.deleteDisabledUsers();
		return ResponseEntity.noContent().build();
	}

}
