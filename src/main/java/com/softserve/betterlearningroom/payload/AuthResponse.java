package com.softserve.betterlearningroom.payload;

import com.softserve.betterlearningroom.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthResponse {
    
    private String token;
    
    private UserDTO user;

}
