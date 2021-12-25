package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.UserDao;
import com.softserve.betterlearningroom.entity.CustomUserDetails;
import com.softserve.betterlearningroom.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with email - %s, not found", email)));
        return CustomUserDetails.userToCustomUserDetails(user);
    }

}
