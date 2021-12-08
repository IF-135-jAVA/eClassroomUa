package com.softserwe.betterlearningroom.service;

import com.softserwe.betterlearningroom.model.Classroom;
import com.softserwe.betterlearningroom.model.User;

import java.util.List;

public interface ClassroomService {

    List<Classroom> getClassroomById(Long classroomId);

    void addClassroom(Classroom classroom);

    List<User> getClassroomOwner(Long ownerId);

    List<User> getClassroomTeachers(Long classroomId);
}
