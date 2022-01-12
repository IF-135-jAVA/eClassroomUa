package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.request.AuthRequest;
import com.softserve.betterlearningroom.entity.request.SaveUserRequest;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;

public interface AuthService {

    String login(AuthRequest request, String userRole);

    UserDTO saveUser(SaveUserRequest request) throws UserAlreadyExistsException;

    UserDTO updateUser(SaveUserRequest request, int id) throws UserAlreadyExistsException;

}
