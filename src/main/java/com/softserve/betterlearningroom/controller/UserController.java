package com.softserve.betterlearningroom.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

	private UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findUserById(
			@PathVariable int id) {
		UserDTO user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAllUsers() {
		List<UserDTO> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/student")
	@RolesAllowed(value = { "STUDENT" })
	public ResponseEntity<String> test1() {
		return  ResponseEntity.ok().body("student get");
	}
	
	@GetMapping("/teacher")
	@RolesAllowed(value = { "TEACHER" })
	public ResponseEntity<String>  test2() {
		return  ResponseEntity.ok().body("teacher get" + " " + System.getenv("CLIENT_ID") + " " + System.getenv("CLIENT_SECRET"));
	}
	
	@GetMapping("/classroom")
	@PreAuthorize("hasAuthority('classroom:write')")
	public ResponseEntity<String> test3() {
		return  ResponseEntity.ok().body("classroom write get");
	}

}
