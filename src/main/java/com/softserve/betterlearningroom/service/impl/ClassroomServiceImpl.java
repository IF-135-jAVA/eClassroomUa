package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.impl.ClassroomDAOImpl;
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

    private ClassroomDAOImpl classroomDaoImpl;
    private ClassroomMapper classroomMapper;
    private UserMapper userMapper;

    public ClassroomDTO findById(String classroomId) {
        Classroom classroom = classroomDaoImpl.findClassroomById(classroomId);
        return classroomMapper.classroomToClassroomDTO(classroom);
    }

    public ClassroomDTO save(ClassroomDTO classroomDTO) {
        return classroomMapper.classroomToClassroomDTO(classroomDaoImpl.save(classroomMapper.classroomDTOToClassroom(classroomDTO)));
    }

    public UserDTO getClassroomOwnerById(String classroomId) {
        User user = classroomDaoImpl.getClassroomOwnerById(classroomId);
        return userMapper.userToUserDTO(user);
    }

    public List<UserDTO> getClassroomTeachersById(String classroomId) {
        return classroomDaoImpl.getAllTeachersById(classroomId).stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getClassroomStudentsById(String classroomId) {
        return classroomDaoImpl.getAllStudentsById(classroomId).stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public List<ClassroomDTO> findAllClassroomsByTeacherId(Long userId) {
        return classroomDaoImpl.findAllClassroomsByTeacherId(userId).stream()
                .map(classroomMapper::classroomToClassroomDTO)
                .collect(Collectors.toList());
    }

    public List<ClassroomDTO> findAllClassroomsByStudentId(Long userId) {
        return classroomDaoImpl.findAllClassroomsByStudentId(userId).stream()
                .map(classroomMapper::classroomToClassroomDTO)
                .collect(Collectors.toList());
    }

    public ClassroomDTO joinClassroomAsStudent(String classroomId, Long userId) {
        Classroom classroom = classroomDaoImpl.findClassroomById(classroomId);
        classroomDaoImpl.joinClassroomAsStudent(classroomId, userId);
        return classroomMapper.classroomToClassroomDTO(classroom);
    }

    public ClassroomDTO joinClassroomAsTeacher(String classroomId, Long userId) {
        Classroom classroom = classroomDaoImpl.findClassroomById(classroomId);
        classroomDaoImpl.joinClassroomAsTeacher(classroomId, userId);
        return classroomMapper.classroomToClassroomDTO(classroom);
    }

    public void delete(String classroomId) {
        ClassroomDTO classroomDTO = findById(classroomId);
        classroomDaoImpl.delete(classroomDTO.getClassroomId());
    }
}
