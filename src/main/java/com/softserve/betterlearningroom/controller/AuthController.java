package com.softserve.betterlearningroom.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.request.AuthRequest;
import com.softserve.betterlearningroom.entity.request.SaveUserRequest;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;
import com.softserve.betterlearningroom.service.AuthService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AuthRequest request,
			@RequestParam String role){
		String token = authService.login(request, role);
		return ResponseEntity.ok().body(token);
	}
	
	@PostMapping("/registration")
	public ResponseEntity<UserDTO> registration(@RequestBody @Valid SaveUserRequest request){
		try {
			UserDTO savedUser = authService.saveUser(request);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedUser.getId())
					.toUri();
			return ResponseEntity.created(location).body(savedUser);
		} catch(UserAlreadyExistsException ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody SaveUserRequest request, @PathVariable int id){
		UserDTO updatedUser = authService.updateUser(request, id);
		return ResponseEntity.ok().body(updatedUser);
	}

}
