package com.softserwe.betterlearningroom.service;

import com.softserwe.betterlearningroom.dao.ClassroomDAO;
import com.softserwe.betterlearningroom.dto.ClassroomDTO;
import com.softserwe.betterlearningroom.entity.Classroom;
import com.softserwe.betterlearningroom.entity.User;
import com.softserwe.betterlearningroom.mapper.ClassroomMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassroomService {

    private ClassroomDAO classroomDAO;
    private ClassroomMapper classroomMapper;

    public ClassroomDTO getClassroomById(Long classroomId) {
        Classroom classroom = classroomDAO.getClassroomById(classroomId);
        return classroomMapper.classroomToClassroomDTO(classroom);
    }

    public void createClassroom(ClassroomDTO classroomDTO) {
        classroomDAO.createClassroom(classroomMapper.classroomDTOToClassroom(classroomDTO));
    }

    public User getClassroomOwnerById(Long ownerId) {
        return classroomDAO.getClassroomOwnerById(ownerId);
    }

    public List<User> getClassroomTeachers(Long classroomId ) {
        return classroomDAO.getClassroomTeachers(classroomId);
    }

    public void removeClassroomById(Long classroomId){
        ClassroomDTO classroomDTO = getClassroomById(classroomId);
        classroomDAO.removeClassroomById(classroomDTO.getClassroomId());
    }
}
