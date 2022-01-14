package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User save(User user);

    User update(User user);

}
