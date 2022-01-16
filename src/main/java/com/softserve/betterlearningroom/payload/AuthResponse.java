package com.softserve.betterlearningroom.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthResponse {
    
    private String token;
    
    private String tokenType ;

}
