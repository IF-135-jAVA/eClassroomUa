package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;

import java.util.List;

public interface ClassroomDAO {

    Classroom findClassroomById(String classroomId);

    List<User> getAllTeachersById(String classroomId);

    List<User> getAllStudentsById(String classroomId);

    User getClassroomOwnerById(String classroomId);

    List<Classroom> findAllClassroomsByTeacherId(Long userId);

    List<Classroom> findAllClassroomsByStudentId(Long userId);

    Classroom joinClassroomAsStudent(String classroomId, Long userId);

    Classroom joinClassroomAsTeacher(String classroomId, Long userId);

    Classroom save(Classroom classroom);

    void delete(String classroomId);
}
