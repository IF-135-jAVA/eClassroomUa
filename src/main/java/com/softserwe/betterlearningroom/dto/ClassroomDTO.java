package com.softserwe.betterlearningroom.dto;

import com.softserwe.betterlearningroom.entity.Announcment;
import com.softserwe.betterlearningroom.entity.Topic;
import com.softserwe.betterlearningroom.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDTO {

    private Long classroomId;

    private String classroomTitle;

    private String classroomSession;

    private String classroomDescription;

    private String classroomCode;

    private User classroomOwner;

    private List<User> classroomTeachers;

    private List<User> classroomStudents;

    private List<Topic> classroomTopics;

    private List<Announcment> classroomAnnouncements;

}
