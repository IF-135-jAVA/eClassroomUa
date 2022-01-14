package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.dto.UserDTO;

import java.util.List;

public interface ClassroomService {

    ClassroomDTO getClassroomById(Long classroomId);

    void createClassroom(ClassroomDTO classroomDTO);

    UserDTO getClassroomOwnerById(Long classroomId);

    List<UserDTO> getClassroomTeachers(Long classroomId);

    List<UserDTO> getClassroomStudents(Long classroomId);

    List<ClassroomDTO> getClassroomsByTeacher(Long userId);

    List<ClassroomDTO> getClassroomsByStudent(Long userId);

    ClassroomDTO joinClassroomAsStudent(String code, Long userId);

    ClassroomDTO joinClassroomAsTeacher(String code, Long userId);

    void removeClassroomById(Long classroomId);
}
