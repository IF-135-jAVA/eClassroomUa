package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.entity.Classroom;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {

    public ClassroomDTO classroomToClassroomDTO(Classroom classroom) {
        ClassroomDTO classroomDTO = new ClassroomDTO();
        classroomDTO.setClassroomId(classroom.getClassroomId());
        classroomDTO.setClassroomTitle(classroom.getClassroomTitle());
        classroomDTO.setClassroomSession(classroom.getClassroomSession());
        classroomDTO.setClassroomDescription(classroom.getClassroomDescription());
        classroomDTO.setClassroomCode(classroom.getClassroomCode());
        classroomDTO.setClassroomOwner(classroom.getClassroomOwner());
        classroomDTO.setClassroomTeachers(classroom.getClassroomTeachers());
        classroomDTO.setClassroomStudents(classroom.getClassroomStudents());
        classroomDTO.setClassroomTopics(classroom.getClassroomTopics());
        classroomDTO.setClassroomAnnouncements(classroom.getClassroomAnnouncements());

        return classroomDTO;

    }

    public Classroom classroomDTOToClassroom(ClassroomDTO classroomDTO) {
        Classroom classroom = new Classroom();
        classroom.setClassroomId(classroomDTO.getClassroomId());
        classroom.setClassroomTitle(classroomDTO.getClassroomTitle());
        classroom.setClassroomSession(classroomDTO.getClassroomSession());
        classroom.setClassroomDescription(classroomDTO.getClassroomDescription());
        classroom.setClassroomCode(classroomDTO.getClassroomCode());
        classroom.setClassroomOwner(classroomDTO.getClassroomOwner());
        classroom.setClassroomTeachers(classroomDTO.getClassroomTeachers());
        classroom.setClassroomStudents(classroomDTO.getClassroomStudents());
        classroom.setClassroomTopics(classroomDTO.getClassroomTopics());
        classroom.setClassroomAnnouncements(classroomDTO.getClassroomAnnouncements());

        return classroom;
    }
}
