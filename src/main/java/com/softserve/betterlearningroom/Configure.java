package com.softserve.betterlearningroom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configure {

    @Bean
public Announcement announcement(){
    return new Announcement();
}
@Bean
    public Comment comment(){
        return  new Comment();
}
}
