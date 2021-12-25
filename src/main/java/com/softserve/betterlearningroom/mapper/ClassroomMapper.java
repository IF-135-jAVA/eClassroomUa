package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.entity.Classroom;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {

    public ClassroomDTO classroomToClassroomDTO(Classroom classroom) {
        ClassroomDTO classroomDTO = new ClassroomDTO();
        classroomDTO.setClassroomId(classroom.getClassroomId());
        classroomDTO.setTitle(classroom.getTitle());
        classroomDTO.setSession(classroom.getSession());
        classroomDTO.setDescription(classroom.getDescription());
        classroomDTO.setCode(classroom.getCode());
        classroomDTO.setUserId(classroom.getUserId());
        classroomDTO.setTeachers(classroom.getTeachers());
        classroomDTO.setStudents(classroom.getStudents());
        classroomDTO.setTopics(classroom.getTopics());
        classroomDTO.setAnnouncements(classroom.getAnnouncements());

        return classroomDTO;

    }

    public Classroom classroomDTOToClassroom(ClassroomDTO classroomDTO) {
        Classroom classroom = new Classroom();
        classroom.setClassroomId(classroomDTO.getClassroomId());
        classroom.setTitle(classroomDTO.getTitle());
        classroom.setSession(classroomDTO.getSession());
        classroom.setDescription(classroomDTO.getDescription());
        classroom.setCode(classroomDTO.getCode());
        classroom.setUserId(classroomDTO.getUserId());
        classroom.setTeachers(classroomDTO.getTeachers());
        classroom.setStudents(classroomDTO.getStudents());
        classroom.setTopics(classroomDTO.getTopics());
        classroom.setAnnouncements(classroomDTO.getAnnouncements());

        return classroom;
    }
}
