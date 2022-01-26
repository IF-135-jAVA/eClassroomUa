package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.UserDAO;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.entity.UserPrincipal;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    UserDAO userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with email - %s, not found", email)));
        return UserPrincipal.create(user);
    }

}
