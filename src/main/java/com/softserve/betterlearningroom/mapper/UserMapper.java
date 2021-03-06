package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setEnabled(user.isEnabled());
        userDTO.setConfirmed(user.isConfirmed());
        return userDTO;
    }

    public User userDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setEnabled(userDTO.isEnabled());
        user.setConfirmed(userDTO.isConfirmed());
        return user;
    }

}
