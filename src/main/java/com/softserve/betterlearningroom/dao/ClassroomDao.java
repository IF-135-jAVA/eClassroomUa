package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;

import java.util.List;

public interface ClassroomDao {

    Classroom getClassroomById(Long classroomId);

    List<User> getClassroomTeachers(Long classroomId);

    List<User> getClassroomStudents(Long classroomId);

    User getClassroomOwnerById(Long classroomId);

    List<Classroom> getClassroomsByTeacher(Long userId);

    List<Classroom> getClassroomsByStudent(Long userId);

    Classroom joinClassroomAsStudent(String code, Long userId);

    Classroom joinClassroomAsTeacher(String code, Long userId);

    Classroom createClassroom(Classroom classroom);

    void removeClassroomById(Long classroomId);

    Classroom getClassroomByCode(String code);
}
