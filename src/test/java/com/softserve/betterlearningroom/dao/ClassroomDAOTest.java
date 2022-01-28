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
    ClassroomDAOImpl classroomDaoImpl;
    @Autowired
    UserDAOImpl userDaoImpl;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testFindClassroomById() {
        Classroom classroom = prepareClassroomDTO();
        assertEquals((classroom), classroomDaoImpl.findClassroomById(1L));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
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
        assertEquals((asTeacher), classroomDaoImpl.joinClassroomAsTeacher("3v8ev2t", 2L));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testFindAllClassroomsByTeacherId() {
        User user = prepareUserDTO();
        userDaoImpl.save(user);
        List<Classroom> classroomsList = new ArrayList<>();
        classroomsList.add(prepareClassroomDTO());
        assertEquals((classroomsList), classroomDaoImpl.findAllClassroomsByTeacherId(5L));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testFindAllClassroomsByStudentId() {
        User user = prepareUserDTO();
        userDaoImpl.save(user);
        List<Classroom> classroomsList = new ArrayList<>();
        classroomsList.add(prepareClassroomDTO());
        assertEquals(classroomsList, classroomDaoImpl.findAllClassroomsByStudentId(5L));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testSave() {
        Classroom classroom = prepareClassroomDTO();
        Classroom createClassroom = classroomDaoImpl.save(classroom);
        assertNotNull(createClassroom);
        assertEquals(1L, createClassroom.getClassroomId());
        assertEquals(5L, createClassroom.getUserId());
        assertEquals("English Language", createClassroom.getTitle());
        assertEquals("Present Simple", createClassroom.getSession());
        assertEquals("The Present Simple Tense", createClassroom.getDescription());
        assertEquals("3v8ev2t", createClassroom.getCode());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testFindByCode() {
        Classroom byCode = classroomDaoImpl.findByCode(prepareClassroomDTO().getCode());
        assertNotNull(byCode);
        assertEquals((byCode), classroomDaoImpl.findByCode("3v8ev2t"));
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
                .firstName("Yurii")
                .lastName("Cheban")
                .email("yuriicheban@gmail.com")
                .password("$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e")
                .enabled(true)
                .provider("local")
                .providerId("111")
                .build();
    }
}
