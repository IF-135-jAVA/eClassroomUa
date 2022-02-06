package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmationToken {
    
    private Long id;
    private String code;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private User user;

}
