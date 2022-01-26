package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.impl.ClassroomDAOImpl;
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

@SpringBootTest(classes = {TestDBConfiguration.class, ClassroomDAOImpl.class, UserDAOImpl.class})
class ClassroomDAOTest {

    @Autowired
    ClassroomDAOImpl ClassroomDAOImpl;
    @Autowired
    UserDAOImpl userDaoImpl;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testGetClassroomById() {
        Classroom classroom = prepareClassroomDTO();
        assertEquals((classroom), ClassroomDAOImpl.findClassroomById(1L));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testGetClassroomTeachers() {
        List<User> teachersList = new ArrayList<>();

        teachersList.add(prepareUserDTO());
        userDaoImpl.save(prepareUserDTO());


        assertEquals(userDaoImpl.findById(5L), ClassroomDAOImpl.getAllTeachersById(2L));
    }

    @Test
    void testGetClassroomStudents() {
        List<User> teachersList = new ArrayList<>();
        teachersList.add(prepareUserDTO());
        userDaoImpl.save(prepareUserDTO());
        assertEquals((teachersList), ClassroomDAOImpl.getAllStudentsById(2L));
    }

    @Test
    void testGetClassroomOwnerById() {
        User owner = prepareUserDTO();

        userDaoImpl.save(owner);
        assertNotNull(owner);
        assertEquals((owner), ClassroomDAOImpl.getClassroomOwnerById(5L));
    }

    @Test
    void testJoinClassroomAsStudent() {
        Classroom asStudent = ClassroomDAOImpl.joinClassroomAsStudent(prepareClassroomDTO().getCode(), prepareClassroomDTO().getUserId());
        assertNotNull(asStudent);
        assertEquals((asStudent), ClassroomDAOImpl.joinClassroomAsStudent("3v8ev2t", 1L));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testJoinClassroomAsTeacher() {
        Classroom asTeacher = ClassroomDAOImpl.joinClassroomAsTeacher(prepareClassroomDTO().getCode(), prepareClassroomDTO().getUserId());
        assertNotNull(asTeacher);
        assertEquals((asTeacher), ClassroomDAOImpl.joinClassroomAsTeacher("3v8ev2t", 1L));
    }

    @Test
    void testGetClassroomsByTeacher() {
        List<Classroom> classroomsList = new ArrayList<>();
        classroomsList.add(prepareClassroomDTO());
        assertEquals((classroomsList), ClassroomDAOImpl.findAllClassroomsByTeacherId(2L));
    }

    @Test
    void testGetClassroomsByStudents() {
        List<Classroom> classroomsList = new ArrayList<>();
        classroomsList.add(prepareClassroomDTO());
        assertEquals((classroomsList), ClassroomDAOImpl.findAllClassroomsByStudentId(2L));
    }

    @Test
    void testCreateClassroom() {
        Classroom classroom = prepareClassroomDTO();
        Classroom createClassroom = ClassroomDAOImpl.save(classroom);
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
        Classroom byCode = ClassroomDAOImpl.findByCode((prepareClassroomDTO().getCode()));
        assertNotNull(byCode);
        assertEquals((byCode), ClassroomDAOImpl.findByCode("3v8ev2t"));
    }

    private Classroom prepareClassroomDTO() {
        return Classroom.builder()
                .classroomId(1L)
                .userId(5L)
                .title("English Language")
                .session("Present Simple")
                .description("The Present Simple Tense")
                .code("3v8ev2t")
                .build();
    }

    private User prepareUserDTO() {
        return User.builder()
                .id(5L)
                .firstName("Yurii")
                .lastName("Cheban")
                .email("yuriicheban@gmail.com")
                .password("$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e")
                .enabled(true)
                .providerId("ddd")
                .provider("fff")
                .build();
    }
}