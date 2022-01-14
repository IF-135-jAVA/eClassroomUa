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

import static org.junit.Assert.*;

@SpringBootTest(classes = {TestDBConfiguration.class, ClassroomDaoImpl.class, UserDAOImpl.class})
public class ClassroomDaoTest {

    @Autowired
    ClassroomDaoImpl classroomDaoImpl;
    @Autowired
    UserDAOImpl userDaoImpl;

    @Test
    public void testCreateClassroomAndGetClassroomById() {
        Classroom classroom = Classroom.builder()
                .classroomId(5L)
                .userId(5L)
                .title("English Language")
                .session("Present Simple")
                .description("The Present Simple Tense")
                .code("3v8ev2t")
                .build();
        classroomDaoImpl.createClassroom(classroom);
        Classroom byId = classroomDaoImpl.getClassroomById(classroom.getClassroomId());
        assertNotNull(byId);
        assertEquals(classroom.getClassroomId(), byId.getClassroomId());
        assertEquals(classroom.getUserId(), byId.getUserId());
        assertEquals(classroom.getTitle(), byId.getTitle());
        assertEquals(classroom.getSession(), byId.getSession());
        assertEquals(classroom.getDescription(), byId.getDescription());
        assertEquals(classroom.getCode(), byId.getCode());
    }

    @Test
    public void testGetClassroomByCode() {
        Classroom classroom = Classroom.builder()
                .classroomId(6L)
                .userId(6L)
                .title("Ukraine Language")
                .session("Plurals")
                .description("You will learn how to make plurals in Ukrainian")
                .code("1y9eg3t")
                .build();
        classroomDaoImpl.createClassroom(classroom);
        Classroom byCode = classroomDaoImpl.getClassroomByCode(classroom.getCode());
        assertNotNull(byCode);
        assertEquals(classroom, byCode);
    }

    @Test
    public void testJoinClassroomAsStudent() {
        Classroom classroom = Classroom.builder()
                .classroomId(7L)
                .userId(7L)
                .title("Biology")
                .session("Genetics")
                .description("Genetics, Genomes, Chromosomes and the Cell Cycle")
                .code("1ef7g1t")
                .build();
        classroomDaoImpl.createClassroom(classroom);
        Classroom asStudent = classroomDaoImpl.joinClassroomAsStudent(classroom.getCode(), classroom.getUserId());
        assertNotNull(asStudent);
        assertEquals(classroom, asStudent);
    }

    @Test
    public void testJoinClassroomAsTeacher() {
        Classroom classroom = Classroom.builder()
                .classroomId(8L)
                .userId(8L)
                .title("Art")
                .session("Sculptures")
                .description("The Thinker")
                .code("g6hg1t5")
                .build();
        classroomDaoImpl.createClassroom(classroom);
        Classroom asTeacher = classroomDaoImpl.joinClassroomAsTeacher(classroom.getCode(), classroom.getUserId());
        assertNotNull(asTeacher);
        assertEquals(classroom, asTeacher);
    }


    @Test
    public void testGetClassroomOwnerById() {
        Classroom classroom = Classroom.builder()
                .classroomId(3L)
                .userId(3L)
                .title("Culture")
                .session("Culture, Arts, and History")
                .description("Students will learn history, arts, culture, and other factors of nations and regions around the world")
                .code("2fe4r1m")
                .build();
        classroomDaoImpl.createClassroom(classroom);
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
    public void getClassroomTeachers() {
        Classroom classroom = Classroom.builder()
                .classroomId(2L)
                .userId(2L)
                .title("Physics")
                .session("Waves")
                .description("It deals with the study of the propagation of energy through space")
                .code("7yu4r1f")
                .build();
        classroomDaoImpl.createClassroom(classroom);
        User user = User.builder()
                .firstName("John")
                .lastName("Rambo")
                .email("johnrambo@gmail.com")
                .password("$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e")
                .enabled(true)
                .build();
        List<User> teachers = new ArrayList();
        teachers.add(user);
        userDaoImpl.save(user);
        List<User> classroomTeachers = classroomDaoImpl.getClassroomTeachers(classroom.getClassroomId());
        assertNotNull(classroomTeachers);
        assertEquals(teachers, classroomTeachers);
    }

    @Test
    public void getClassroomStudents() {
        Classroom classroom = Classroom.builder()
                .classroomId(1L)
                .userId(1L)
                .title("Philosophy")
                .session("General philosophy")
                .description("Philosophy is the study of thought concerning nature, metaphysics, ethics, aesthetics, being, knowledge, logic, and all manner of theory")
                .code("5ge8r5j")
                .build();
        classroomDaoImpl.createClassroom(classroom);
        User user = User.builder()
                .firstName("Kevin")
                .lastName("McCallister")
                .email("kevinmcallister@gmail.com")
                .password("$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e")
                .enabled(true)
                .build();
        List<User> students = new ArrayList();
        students.add(user);
        userDaoImpl.save(user);
        List<User> classroomStudents = classroomDaoImpl.getClassroomStudents(classroom.getClassroomId());
        assertNotNull(classroomStudents);
        assertEquals(students, classroomStudents);
    }

//    @Test
//    public void testGetClassroomsByStudent() {
//        User user = User.builder()
//                .firstName("Slevin")
//                .lastName("Kelevra")
//                .email("slevinkelevra@gmail.com")
//                .password("$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e")
//                .enabled(true)
//                .build();
//        userDaoImpl.save(user);
//        Classroom classroom = Classroom.builder()
//                .classroomId(1L)
//                .userId(1L)
//                .title("Mathematics")
//                .session("General and overarching topics: collections")
//                .description("Introductory exposition pertaining to mathematics in general")
//                .code("9e8gh1t")
//                .build();
//        List<Classroom> classrooms = new ArrayList();
//        classrooms.add(classroom);
//        classroomDaoImpl.createClassroom(classroom);
//        List<Classroom> classroomsByStudent = classroomDaoImpl.getClassroomsByStudent(classroom.getUserId());
//        assertNotNull(classroomsByStudent);
//        assertEquals(classrooms, classroomsByStudent);
//    }
}
