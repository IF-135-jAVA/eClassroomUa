package com.softserve.betterlearningroom.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChangePasswordRequest {
    
    private String password;
    private String token;

}
