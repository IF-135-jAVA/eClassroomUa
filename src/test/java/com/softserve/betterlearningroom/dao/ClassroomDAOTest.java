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

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals((classroom), classroomDaoImpl.findClassroomById("04aa5bec-01aa-4dc9-b57d-37669dd72c47"));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testGetClassroomOwnerById() {
        User owner = prepareUserDTO();
        userDaoImpl.save(owner);
        assertNotNull(owner);
        assertEquals((owner), classroomDaoImpl.getClassroomOwnerById("04aa5bec-01aa-4dc9-b57d-37669dd72c47"));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testJoinClassroomAsStudent() {
        Classroom asStudent = classroomDaoImpl.joinClassroomAsStudent(prepareClassroomDTO().getClassroomId(), prepareClassroomDTO().getUserId());
        assertNotNull(asStudent);
        assertEquals((asStudent), classroomDaoImpl.joinClassroomAsStudent("04aa5bec-01aa-4dc9-b57d-37669dd72c47", 1L));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testJoinClassroomAsTeacher() {
        Classroom asTeacher = classroomDaoImpl.joinClassroomAsTeacher(prepareClassroomDTO().getClassroomId(), prepareClassroomDTO().getUserId());
        assertNotNull(asTeacher);
        assertEquals((asTeacher), classroomDaoImpl.joinClassroomAsTeacher("04aa5bec-01aa-4dc9-b57d-37669dd72c47", 2L));
    }

    private Classroom prepareClassroomDTO() {
        return Classroom.builder()
                .classroomId("04aa5bec-01aa-4dc9-b57d-37669dd72c47")
                .userId(5L)
                .title("English Language")
                .session("Present Simple")
                .description("The Present Simple Tense")
                .enabled(true)
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
