package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;
import com.softserve.betterlearningroom.payload.AuthRequest;
import com.softserve.betterlearningroom.payload.AuthResponse;
import com.softserve.betterlearningroom.payload.SaveUserRequest;
import com.softserve.betterlearningroom.service.AuthService;
import com.softserve.betterlearningroom.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request, @RequestParam String role) {
        String token = authService.login(request, role);
        UserDTO user = userService.findByEmail(request.getLogin());
        return ResponseEntity.ok().body(new AuthResponse(token, user));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registration(@RequestBody @Valid SaveUserRequest request) {
        try {
            UserDTO savedUser = authService.saveUser(request);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedUser.getId()).toUri();
            return ResponseEntity.created(location).body(savedUser);
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid SaveUserRequest request, @PathVariable Long id) throws UserAlreadyExistsException {
        UserDTO updatedUser = authService.updateUser(request, id);
        return ResponseEntity.ok().body(updatedUser);
    }

}
