package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(Long id);

    UserDTO findByEmail(String email);

    List<UserDTO> findAll();

}
