package com.softserve.betterlearningroom.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {
    private String login;
    private String password;
}
