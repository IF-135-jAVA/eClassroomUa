package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.impl.ClassroomDaoImpl;
import com.softserve.betterlearningroom.dao.impl.UserDAOImpl;
import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void testCreateClassroomAndGetClassroomById() {
        Classroom classroom = Classroom.builder()
                .classroomId(5L)
                .userId(5L)
                .title("English Language")
                .session("Present Simple")
                .description("The Present Simple Tense")
                .code("3v8ev2t")
                .build();
        classroomDaoImpl.save(classroom);
        Classroom byId = classroomDaoImpl.findClassroomById(classroom.getClassroomId());
        assertNotNull(byId);
        assertEquals(classroom.getClassroomId(), byId.getClassroomId());
        assertEquals(classroom.getUserId(), byId.getUserId());
        assertEquals(classroom.getTitle(), byId.getTitle());
        assertEquals(classroom.getSession(), byId.getSession());
        assertEquals(classroom.getDescription(), byId.getDescription());
        assertEquals(classroom.getCode(), byId.getCode());
    }

    @Test
    void testGetClassroomByCode() {
        Classroom classroom = Classroom.builder()
                .classroomId(6L)
                .userId(6L)
                .title("Ukraine Language")
                .session("Plurals")
                .description("You will learn how to make plurals in Ukrainian")
                .code("1y9eg3t")
                .build();
        classroomDaoImpl.save(classroom);
        Classroom byCode = classroomDaoImpl.findByCode(classroom.getCode());
        assertNotNull(byCode);
        assertEquals(classroom, byCode);
    }

    @Test
    void testJoinClassroomAsStudent() {
        Classroom classroom = Classroom.builder()
                .classroomId(7L)
                .userId(7L)
                .title("Biology")
                .session("Genetics")
                .description("Genetics, Genomes, Chromosomes and the Cell Cycle")
                .code("1ef7g1t")
                .build();
        classroomDaoImpl.save(classroom);
        Classroom asStudent = classroomDaoImpl.joinClassroomAsStudent(classroom.getCode(), classroom.getUserId());
        assertNotNull(asStudent);
        assertEquals(classroom, asStudent);
    }

    @Test
    void testJoinClassroomAsTeacher() {
        Classroom classroom = Classroom.builder()
                .classroomId(8L)
                .userId(8L)
                .title("Art")
                .session("Sculptures")
                .description("The Thinker")
                .code("g6hg1t5")
                .build();
        classroomDaoImpl.save(classroom);
        Classroom asTeacher = classroomDaoImpl.joinClassroomAsTeacher(classroom.getCode(), classroom.getUserId());
        assertNotNull(asTeacher);
        assertEquals(classroom, asTeacher);
    }


    @Test
    void testGetClassroomOwnerById() {
        Classroom classroom = Classroom.builder()
                .classroomId(3L)
                .userId(3L)
                .title("Culture")
                .session("Culture, Arts, and History")
                .description("Students will learn history, arts, culture, and other factors of nations and regions around the world")
                .code("2fe4r1m")
                .build();
        classroomDaoImpl.save(classroom);
        User user = User.builder()
                .firstName("Yurii")
                .lastName("Cheban")
                .email("yuriicheban@gmail.com")
                .password("$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e")
                .enabled(true)
                .build();
        userDaoImpl.save(user);
        User classroomOwnerById = classroomDaoImpl.getClassroomOwnerById(classroom.getClassroomId());
        assertNotNull(classroomOwnerById);
        assertEquals(user, classroomOwnerById);

    }

    @Test
    void getClassroomTeachers() {
        Classroom classroom = Classroom.builder()
                .classroomId(2L)
                .userId(2L)
                .title("Physics")
                .session("Waves")
                .description("It deals with the study of the propagation of energy through space")
                .code("7yu4r1f")
                .build();
        classroomDaoImpl.save(classroom);
        User user = User.builder()
                .firstName("John")
                .lastName("Rambo")
                .email("johnrambo@gmail.com")
                .password("$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e")
                .enabled(true)
                .build();
        List<User> teachers = new ArrayList<User>();
        teachers.add(user);
        userDaoImpl.save(user);
        List<User> classroomTeachers = classroomDaoImpl.getAllTeachersById(classroom.getClassroomId());
        assertNotNull(classroomTeachers);
        assertEquals(teachers, classroomTeachers);
    }

    @Test
    void getClassroomStudents() {
        Classroom classroom = Classroom.builder()
                .classroomId(1L)
                .userId(1L)
                .title("Philosophy")
                .session("General philosophy")
                .description("Philosophy is the study of thought concerning nature, metaphysics, ethics, aesthetics, being, knowledge, logic, and all manner of theory")
                .code("5ge8r5j")
                .build();
        classroomDaoImpl.save(classroom);
        User user = User.builder()
                .firstName("Kevin")
                .lastName("McCallister")
                .email("kevinmcallister@gmail.com")
                .password("$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e")
                .enabled(true)
                .build();
        List<User> students = new ArrayList<User>();
        students.add(user);
        userDaoImpl.save(user);
        List<User> classroomStudents = classroomDaoImpl.getAllStudentsById(classroom.getClassroomId());
        assertNotNull(classroomStudents);
        assertEquals(students, classroomStudents);
    }
}
