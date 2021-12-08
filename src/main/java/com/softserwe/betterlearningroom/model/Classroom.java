package com.softserwe.betterlearningroom.model;

import java.util.List;

public class Classroom {

    private Long classroomId;

    private String classroomTitle;

    private String classroomSession;

    private String description;

    private String code;

    private User classroomOwner;

    private List<User> classroomTeachers;

    private List<User> classroomStudents;

    private List<Topic> topics;

    private List<Announcment> announcements;

}
