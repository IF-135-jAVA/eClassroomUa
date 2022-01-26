package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.dto.UserDTO;

import java.util.List;

public interface ClassroomService {

    ClassroomDTO findById(Long classroomId);

    ClassroomDTO save(ClassroomDTO classroomDTO);

    UserDTO getClassroomOwnerById(Long classroomId);

    List<UserDTO> getClassroomTeachersById(Long classroomId);

    List<UserDTO> getClassroomStudentsById(Long classroomId);

    List<ClassroomDTO> findAllClassroomsByTeacherId(Long userId);

    List<ClassroomDTO> findAllClassroomsByStudentId(Long userId);

    ClassroomDTO joinClassroomAsStudent(String code, Long userId);

    ClassroomDTO joinClassroomAsTeacher(String code, Long userId);

    void delete(Long classroomId);
}
