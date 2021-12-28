package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    List<User> findAll();

    Optional<User> findById(int id);

    Optional<User> findByEmail(String email);

    void save(User user);

    void update(User user);

}
