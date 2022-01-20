package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.impl.ClassroomDaoImpl;
import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.mapper.ClassroomMapper;
import com.softserve.betterlearningroom.mapper.UserMapper;
import com.softserve.betterlearningroom.service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private ClassroomDaoImpl classroomDaoImpl;
    private ClassroomMapper classroomMapper;
    private UserMapper userMapper;

    public ClassroomDTO getClassroomById(Long classroomId) {
        Classroom classroom = classroomDaoImpl.getClassroomById(classroomId);
        return classroomMapper.classroomToClassroomDTO(classroom);
    }

    public ClassroomDTO createClassroom(ClassroomDTO classroomDTO) {
        return classroomMapper.classroomToClassroomDTO(classroomDaoImpl.createClassroom(classroomMapper.classroomDTOToClassroom(classroomDTO)));
    }

    public UserDTO getClassroomOwnerById(Long classroomId) {
        User user = classroomDaoImpl.getClassroomOwnerById(classroomId);
        return userMapper.userToUserDTO(user);
    }

    public List<UserDTO> getClassroomTeachers(Long classroomId) {
        return classroomDaoImpl.getClassroomTeachers(classroomId).stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getClassroomStudents(Long classroomId) {
        return classroomDaoImpl.getClassroomStudents(classroomId).stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public List<ClassroomDTO> getClassroomsByTeacher(Long userId) {
        return classroomDaoImpl.getClassroomsByTeacher(userId).stream()
                .map(classroomMapper::classroomToClassroomDTO)
                .collect(Collectors.toList());
    }

    public List<ClassroomDTO> getClassroomsByStudent(Long userId) {
        return classroomDaoImpl.getClassroomsByStudent(userId).stream()
                .map(classroomMapper::classroomToClassroomDTO)
                .collect(Collectors.toList());
    }

    public ClassroomDTO joinClassroomAsStudent(String code, Long userId) {
        Classroom classroom = classroomDaoImpl.getClassroomByCode(code);
        if (classroomDaoImpl.getClassroomTeachers(classroom.getClassroomId()).stream().anyMatch(user -> user.getId() == userId)) {
        } else if (classroomDaoImpl.getClassroomStudents(classroom.getClassroomId()).stream().anyMatch(user -> user.getId() == userId)) {
        } else if (classroomDaoImpl.getClassroomOwnerById(classroom.getClassroomId()).getId() == userId) {
        } else {
            classroomDaoImpl.joinClassroomAsStudent(code, userId);
            return classroomMapper.classroomToClassroomDTO(classroom);
        }
        return null;
    }

    public ClassroomDTO joinClassroomAsTeacher(String code, Long userId) {
        Classroom classroom = classroomDaoImpl.getClassroomByCode(code);
        if (classroomDaoImpl.getClassroomStudents(classroom.getClassroomId()).stream().anyMatch(user -> user.getId() == userId)) {
        } else if (classroomDaoImpl.getClassroomTeachers(classroom.getClassroomId()).stream().anyMatch(user -> user.getId() == userId)) {
        } else if (classroomDaoImpl.getClassroomOwnerById(classroom.getClassroomId()).getId() == userId) {
        } else {
            classroomDaoImpl.joinClassroomAsTeacher(code, userId);
            return classroomMapper.classroomToClassroomDTO(classroom);
        }
        return null;
    }

    public void removeClassroomById(Long classroomId) {
        ClassroomDTO classroomDTO = getClassroomById(classroomId);
        classroomDaoImpl.removeClassroomById(classroomDTO.getClassroomId());
    }
}