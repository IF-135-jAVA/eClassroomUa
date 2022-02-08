package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.entity.Classroom;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {

    public ClassroomDTO classroomToClassroomDTO(Classroom classroom) {
        ClassroomDTO classroomDTO = new ClassroomDTO();
        classroomDTO.setClassroomId(classroom.getClassroomId());
        classroomDTO.setUserId(classroom.getUserId());
        classroomDTO.setTitle(classroom.getTitle());
        classroomDTO.setSession(classroom.getSession());
        classroomDTO.setDescription(classroom.getDescription());
        classroomDTO.setEnabled(classroom.isEnabled());
        classroomDTO.setOwner(classroom.getOwner());
        classroomDTO.setTeachers(classroom.getTeachers());
        classroomDTO.setStudents(classroom.getStudents());
        classroomDTO.setTopics(classroom.getTopics());
        classroomDTO.setAnnouncements(classroom.getAnnouncements());

        return classroomDTO;

    }

    public Classroom classroomDTOToClassroom(ClassroomDTO classroomDTO) {
        Classroom classroom = new Classroom();
        classroom.setClassroomId(classroomDTO.getClassroomId());
        classroom.setUserId(classroomDTO.getUserId());
        classroom.setTitle(classroomDTO.getTitle());
        classroom.setSession(classroomDTO.getSession());
        classroom.setDescription(classroomDTO.getDescription());
        classroom.setEnabled(classroomDTO.isEnabled());
        classroom.setOwner(classroomDTO.getOwner());
        classroom.setTeachers(classroomDTO.getTeachers());
        classroom.setStudents(classroomDTO.getStudents());
        classroom.setTopics(classroomDTO.getTopics());
        classroom.setAnnouncements(classroomDTO.getAnnouncements());

        return classroom;
    }
}
