package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.UserDTO;

import java.util.List;

public interface UserService {

    /**
     * Finds user by id.
     * @param 
     *        id Id of the user.
     * @exception UsernameNotFoundException when user not found.
     * @return {@link UserDTO} with specified id.
     */
    UserDTO findById(Long id);

    /**
     * Finds user by email.
     * @param 
     *       email The user email.
     * @exception UsernameNotFoundException when user not found.
     * @return {@link UserDTO} with specified email.
     */
    UserDTO findByEmail(String email);

    /**
     * Finds all available users in the database.
     * @return List of {@link UserDTO}.
     */
    List<UserDTO> findAll();
}
