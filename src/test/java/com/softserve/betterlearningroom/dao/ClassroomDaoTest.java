package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.impl.ClassroomDaoImpl;
import com.softserve.betterlearningroom.dao.impl.UserDAOImpl;
import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {TestDBConfiguration.class, ClassroomDaoImpl.class, UserDAOImpl.class})
class ClassroomDaoTest {

    @Autowired
    ClassroomDaoImpl classroomDaoImpl;
    @Autowired
    UserDAOImpl userDaoImpl;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testGetClassroomById() {
        Classroom classroom = prepareClassroomDTO();
        assertEquals((classroom), classroomDaoImpl.getClassroomById(1L));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testGetClassroomTeachers() {
        List<User> teachersList = new ArrayList<>();
        teachersList.add(prepareUserDTO());
        userDaoImpl.save(prepareUserDTO());
        assertEquals((teachersList), classroomDaoImpl.getClassroomTeachers(2L));
    }

    @Test
    void testGetClassroomStudents() {
        List<User> teachersList = new ArrayList<>();
        teachersList.add(prepareUserDTO());
        userDaoImpl.save(prepareUserDTO());
        assertEquals((teachersList), classroomDaoImpl.getClassroomStudents(2L));
    }

    @Test
    void testGetClassroomOwnerById() {
        User owner = prepareUserDTO();
        userDaoImpl.save(owner);
        assertNotNull(owner);
        assertEquals((owner), classroomDaoImpl.getClassroomOwnerById(2L));
    }

    @Test
    void testJoinClassroomAsStudent() {
        Classroom asStudent = classroomDaoImpl.joinClassroomAsStudent(prepareClassroomDTO().getCode(), prepareClassroomDTO().getUserId());
        assertNotNull(asStudent);
        assertEquals((asStudent), classroomDaoImpl.joinClassroomAsStudent("3v8ev2t", 1L));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testJoinClassroomAsTeacher() {
        Classroom asTeacher = classroomDaoImpl.joinClassroomAsTeacher(prepareClassroomDTO().getCode(), prepareClassroomDTO().getUserId());
        assertNotNull(asTeacher);
        assertEquals((asTeacher), classroomDaoImpl.joinClassroomAsTeacher("3v8ev2t", 1L));
    }

    @Test
    void testGetClassroomsByTeacher() {
        List<Classroom> classroomsList = new ArrayList<>();
        classroomsList.add(prepareClassroomDTO());
        assertEquals((classroomsList), classroomDaoImpl.getClassroomsByTeacher(2L));
    }

    @Test
    void testGetClassroomsByStudents() {
        List<Classroom> classroomsList = new ArrayList<>();
        classroomsList.add(prepareClassroomDTO());
        assertEquals((classroomsList), classroomDaoImpl.getClassroomsByStudent(2L));
    }

    @Test
    void testCreateClassroom() {
        Classroom classroom = prepareClassroomDTO();
        Classroom createClassroom = classroomDaoImpl.createClassroom(classroom);
        assertNotNull(createClassroom);
        assertEquals(1L, createClassroom.getClassroomId());
        assertEquals(2L, createClassroom.getUserId());
        assertEquals("English Language", createClassroom.getTitle());
        assertEquals("Present Simple", createClassroom.getSession());
        assertEquals("The Present Simple Tense", createClassroom.getDescription());
        assertEquals("3v8ev2t", createClassroom.getCode());
    }

    @Test
    void testGetClassroomByCode() {
        Classroom byCode = classroomDaoImpl.getClassroomByCode(prepareClassroomDTO().getCode());
        assertNotNull(byCode);
        assertEquals((byCode), classroomDaoImpl.getClassroomByCode("3v8ev2t"));
    }

    private Classroom prepareClassroomDTO() {
        return Classroom.builder()
                .classroomId(1L)
                .userId(2L)
                .title("English Language")
                .session("Present Simple")
                .description("The Present Simple Tense")
                .code("3v8ev2t")
                .build();
    }

    private User prepareUserDTO() {
        return User.builder()
                .id(1L)
                .firstName("Yurii")
                .lastName("Cheban")
                .email("yuriicheban@gmail.com")
                .password("$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e")
                .enabled(true)
                .build();
    }
}
