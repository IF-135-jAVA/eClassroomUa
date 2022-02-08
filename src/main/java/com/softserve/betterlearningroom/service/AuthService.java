package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.exception.TokenExpiredException;
import com.softserve.betterlearningroom.exception.TokenNotFoundException;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;
import com.softserve.betterlearningroom.payload.AuthRequest;
import com.softserve.betterlearningroom.payload.SaveUserRequest;

public interface AuthService {

    /**
     * Checks if the user with login from {@link AuthRequest} exists in the database and whether stored password matches
     * with password from the request. If yes, then return a new Jwt token with empty <b>Role</b>.
     * @param request {@link AuthRequest} Entity, which contains user email and password.
     * @exception BadCredentialsException when user not found, or password does not match.
     * @return {@link String} token.
     */
    String login(AuthRequest request);
    
    /**
     * Generates a new Jwt token with <b>Role</b> chosen by user with <i>username</i> stored in <b>SecurityContextHolder</b>.
     * @param userRole The preferred user role.
     * @return {@link String} token.
     */
    String setRole(String userRole);

    /**
     * Performs an attempt to save a new <b>User</b> to the database.
     * @param request {@link SaveUserRequest} Entity, contains user information. Some fields have <i>@NotBlank</i> constraint.
     * @exception UserAlreadyExistsException when the <b>User</b> with current email already exists.
     * @return {@link UserDTO} created <b>User</b>.
     */
    UserDTO saveUser(SaveUserRequest request) throws UserAlreadyExistsException;

    /**
     * Performs an attempt to update an existing <b>User</b> in the database.
     * @param request {@link SaveUserRequest} Entity, contains user information. Some fields have <i>@NotBlank</i> constraint.
     * @param id Id of an existing <b>User</b>.
     * @exception UsernameNotFoundException when the <b>User</b> with current <i>id</i> not found.
     * @exception UserAlreadyExistsException when the <b>User</b> tries to set email of other existing user.
     * @return {@link UserDTO} updated <b>User</b>.
     */
    UserDTO updateUser(SaveUserRequest request, Long id) throws UserAlreadyExistsException;
    //TODO: write jvdog
    UserDTO confirmUser(String code) throws TokenExpiredException, TokenNotFoundException;

}
