//package com.softserve.betterlearningroom.dao;
//
//import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
//import com.softserve.betterlearningroom.dao.impl.ClassroomDaoImpl;
//import com.softserve.betterlearningroom.entity.Classroom;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//
//@SpringBootTest(classes = {TestDBConfiguration.class, ClassroomDaoImpl.class})
//public class ClassroomDaoTest {
//
//    public static final long CLASSROOM_ID = 1L;
//    public static final long USER_ID = 1L;
//    public static final String ENGLISH = "English Language";
//    public static final String PRESENT_SIMPLE = "Present Simple";
//    public static final String THE_PRESENT_SIMPLE_TENSE = "The Present Simple Tense";
//    public static final String CODE = "3v8ev2t";
//
//    @Autowired
//    ClassroomDaoImpl classroomDaoImpl;
//
//
//    @Test
//    public void testCreateClassroom() {
//        Classroom classroom = Classroom.builder()
//                .classroomId(CLASSROOM_ID)
//                .userId(USER_ID)
//                .title(ENGLISH)
//                .session(PRESENT_SIMPLE)
//                .description(THE_PRESENT_SIMPLE_TENSE)
//                .code(CODE)
//                .build();
//
//
//        classroomDaoImpl.createClassroom(classroom);
//
//        Classroom byId = classroomDaoImpl.getClassroomById(CLASSROOM_ID);
//        assertNotNull(byId);
//        assertEquals(Optional.of(classroom), byId.getClassroomId());
//    }
//
//    @Test
//    public void TestGetClassroomById() {
//
//
//    }
//}
