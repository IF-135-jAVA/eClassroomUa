package com.softserwe.betterlearningroom.service.impl;

import com.softserwe.betterlearningroom.dao.ClassroomDao;
import com.softserwe.betterlearningroom.model.Classroom;
import com.softserwe.betterlearningroom.model.User;
import com.softserwe.betterlearningroom.service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassroomServiceImplementation implements ClassroomService {

    private ClassroomDao classroomDao;

    @Override
    public List<Classroom> getClassroomById(Long id) {
        return classroomDao.getClassroomById(id);
    }

    @Override
    public void addClassroom(Classroom classroom) {
        classroomDao.addClassroom(classroom);
    }

    @Override
    public List<User> getClassroomOwner(Long ownerId) {
        return classroomDao.getClassroomOwner(ownerId);
    }

    @Override
    public List<User> getClassroomTeachers(Long classroomId ) {
        return classroomDao.getClassroomTeachers(classroomId);
    }
}
