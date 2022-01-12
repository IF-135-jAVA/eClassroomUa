package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.impl.ClassroomDaoImpl;
import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.mapper.ClassroomMapper;
import com.softserve.betterlearningroom.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClassroomService {

    private ClassroomDaoImpl classroomDAO;
    private ClassroomMapper classroomMapper;
    private UserMapper userMapper;

    public ClassroomDTO getClassroomById(Long classroomId) {
        Classroom classroom = classroomDAO.getClassroomById(classroomId);
        return classroomMapper.classroomToClassroomDTO(classroom);
    }

    public void createClassroom(ClassroomDTO classroomDTO) {
        classroomDAO.createClassroom(classroomMapper.classroomDTOToClassroom(classroomDTO));
    }

    public UserDTO getClassroomOwnerById(Long classroomId) {
        User user = classroomDAO.getClassroomOwnerById(classroomId);
        return userMapper.userToUserDTO(user);
    }

    public List<UserDTO> getClassroomTeachers(Long classroomId ) {
        return classroomDAO.getClassroomTeachers(classroomId).stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getClassroomStudents(Long classroomId ) {
        return classroomDAO.getClassroomStudents(classroomId).stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public List<ClassroomDTO> getClassroomsByTeacher(Long userId){
        return classroomDAO.getClassroomsByTeacher(userId).stream()
                .map(classroomMapper::classroomToClassroomDTO)
                .collect(Collectors.toList());
    }

    public List<ClassroomDTO> getClassroomsByStudent(Long userId){
        return classroomDAO.getClassroomsByStudent(userId).stream()
                .map(classroomMapper::classroomToClassroomDTO)
                .collect(Collectors.toList());
    }

    public ClassroomDTO joinClassroomAsStudent(String code, Long userId){
        Classroom classroom = classroomDAO.getClassroomByCode(code);
        if (classroomDAO.getClassroomTeachers(classroom.getClassroomId()).stream().anyMatch(user -> user.getId() == userId)) {
        }else if(classroomDAO.getClassroomStudents(classroom.getClassroomId()).stream().anyMatch(user -> user.getId() == userId)){
        }else if(classroomDAO.getClassroomOwnerById(classroom.getClassroomId()).getId() == userId){
        }else{
            classroomDAO.joinClassroomAsStudent(code, userId);
            return classroomMapper.classroomToClassroomDTO(classroom);
        }
        return null;
    }

    public ClassroomDTO joinClassroomAsTeacher(String code, Long userId){
        Classroom classroom = classroomDAO.getClassroomByCode(code);
        if (classroomDAO.getClassroomStudents(classroom.getClassroomId()).stream().anyMatch(user -> user.getId() == userId)){
        }else if(classroomDAO.getClassroomTeachers(classroom.getClassroomId()).stream().anyMatch(user -> user.getId() == userId)){
        }else if(classroomDAO.getClassroomOwnerById(classroom.getClassroomId()).getId() == userId){
        }else {
            classroomDAO.joinClassroomAsTeacher(code, userId);
            return classroomMapper.classroomToClassroomDTO(classroom);
        }
        return null;
    }

    public void removeClassroomById(Long classroomId){
        ClassroomDTO classroomDTO = getClassroomById(classroomId);
        classroomDAO.removeClassroomById(classroomDTO.getClassroomId());
    }
}
