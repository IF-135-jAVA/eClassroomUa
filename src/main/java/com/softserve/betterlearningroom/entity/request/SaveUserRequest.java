package com.softserve.betterlearningroom.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
