package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Classroom {

    private Long classroomId;

    private Long userId;

    private String title;

    private String session;

    private String description;

    private String code;

    private User owner;

    private List<User> teachers;

    private List<User> students;

    private List<Topic> topics;

    private List<Announcement> announcements;
}
