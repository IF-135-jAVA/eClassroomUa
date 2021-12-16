package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.entity.Classroom;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {

    public ClassroomDTO classroomToClassroomDTO(Classroom classroom) {
        ClassroomDTO classroomDTO = new ClassroomDTO();
        classroomDTO.setClassroom_id(classroom.getClassroom_id());
        classroomDTO.setTitle(classroom.getTitle());
        classroomDTO.setSession(classroom.getSession());
        classroomDTO.setDescription(classroom.getDescription());
        classroomDTO.setCode(classroom.getCode());
        classroomDTO.setUser_id(classroom.getUser_id());
        classroomDTO.setTeachers(classroom.getTeachers());
        classroomDTO.setStudents(classroom.getStudents());
        classroomDTO.setTopics(classroom.getTopics());
        classroomDTO.setAnnouncements(classroom.getAnnouncements());

        return classroomDTO;

    }

    public Classroom classroomDTOToClassroom(ClassroomDTO classroomDTO) {
        Classroom classroom = new Classroom();
        classroom.setClassroom_id(classroomDTO.getClassroom_id());
        classroom.setTitle(classroomDTO.getTitle());
        classroom.setSession(classroomDTO.getSession());
        classroom.setDescription(classroomDTO.getDescription());
        classroom.setCode(classroomDTO.getCode());
        classroom.setUser_id(classroomDTO.getUser_id());
        classroom.setTeachers(classroomDTO.getTeachers());
        classroom.setStudents(classroomDTO.getStudents());
        classroom.setTopics(classroomDTO.getTopics());
        classroom.setAnnouncements(classroomDTO.getAnnouncements());

        return classroom;
    }
}
