package com.softserve.betterlearningroom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/owner/classrooms/{classroomId}")
	public ResponseEntity<UserDTO> getClassroomOwner(
			@PathVariable int classroomId) {
		UserDTO user = userService.getClassroomOwner(classroomId);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("/teachers/classrooms/{classroomId}")
	public ResponseEntity<List<UserDTO>> getClassroomTeachers(
			@PathVariable int classroomId) {
		List<UserDTO> users = userService.getClassroomTeachers(classroomId);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/students/classrooms/{classroomId}")
	public ResponseEntity<List<UserDTO>> getClassroomStudents(
			@PathVariable int classroomId) {
		List<UserDTO> users = userService.getClassroomStudents(classroomId);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}


}
