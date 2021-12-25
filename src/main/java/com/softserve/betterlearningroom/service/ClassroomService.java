package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.ClassroomDAO;
import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.mapper.ClassroomMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassroomService {

    private ClassroomDAO classroomDAO;
    private ClassroomMapper classroomMapper;

    public ClassroomDTO getClassroomById(Long classroom_id) {
        Classroom classroom = classroomDAO.getClassroomById(classroom_id);
        return classroomMapper.classroomToClassroomDTO(classroom);
    }

    public void createClassroom(ClassroomDTO classroomDTO) {
        classroomDAO.createClassroom(classroomMapper.classroomDTOToClassroom(classroomDTO));
    }

    public User getClassroomOwnerById(Long user_id) {

        return classroomDAO.getClassroomOwnerById(user_id);
    }

    public List<User> getClassroomTeachers(Long classroom_id ) {

        return classroomDAO.getClassroomTeachers(classroom_id);
    }

    public void removeClassroomById(Long classroom_id){
        ClassroomDTO classroomDTO = getClassroomById(classroom_id);
        classroomDAO.removeClassroomById(classroomDTO.getClassroomId());
    }
}
