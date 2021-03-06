package com.softserve.betterlearningroom.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveUserRequest {

    @NotBlank(message = "Firstname must not be empty.")
    private String firstName;

    @NotBlank(message = "Lastname must not be empty.")
    private String lastName;

    @NotBlank(message = "Password must not be empty.")
    private String password;

    @NotBlank(message = "Email must not be empty.")
    @Email(message = "Email must be a valid email address.")
    private String email;

    private boolean enabled;

}
