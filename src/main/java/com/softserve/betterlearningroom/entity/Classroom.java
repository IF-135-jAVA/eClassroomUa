package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {

    private Long classroom_id;

    private Long user_id;

    private String title;

    private String session;

    private String description;

    private String code;

    private List<User> teachers;

    private List<User> students;

    private List<Topic> topics;

    private List<Announcement> announcements;
}
