package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;

import java.util.List;

public interface ClassroomDAO {

    Classroom findClassroomById(Long classroomId);

    List<User> getAllTeachersById(Long classroomId);

    List<User> getAllStudentsById(Long classroomId);

    User getClassroomOwnerById(Long classroomId);

    List<Classroom> findAllClassroomsByTeacherId(Long userId);

    List<Classroom> findAllClassroomsByStudentId(Long userId);

    Classroom joinClassroomAsStudent(Long classroomId, Long userId);

    Classroom joinClassroomAsTeacher(Long classroomId, Long userId);

    Classroom save(Classroom classroom);

    void delete(Long classroomId);
}
