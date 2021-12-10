package com.softserve.betterlearningroom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserve.betterlearningroom.entity.AuthRequest;
import com.softserve.betterlearningroom.service.AuthService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AuthRequest request){
		String token = authService.login(request);
		return ResponseEntity.ok().body(token);
	}
	

}
