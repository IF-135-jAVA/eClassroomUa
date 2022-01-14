package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;
import com.softserve.betterlearningroom.payload.AuthRequest;
import com.softserve.betterlearningroom.payload.SaveUserRequest;

public interface AuthService {

    String login(AuthRequest request, String userRole);

    UserDTO saveUser(SaveUserRequest request) throws UserAlreadyExistsException;

    UserDTO updateUser(SaveUserRequest request, Long id) throws UserAlreadyExistsException;

}
