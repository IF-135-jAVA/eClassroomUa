package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.impl.ClassroomDaoImpl;
import com.softserve.betterlearningroom.entity.Classroom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(classes = {TestDBConfiguration.class, ClassroomDaoImpl.class})
public class ClassroomDaoTest {

    public static final Long CLASSROOM_ID = 1L;
    public static final Long USER_ID = 1L;
    public static final String ENGLISH = "English Language";
    public static final String PRESENT_SIMPLE = "Present Simple";
    public static final String THE_PRESENT_SIMPLE_TENSE = "The Present Simple Tense";
    public static final String CODE = "3v8ev2t";

    @Autowired
    ClassroomDaoImpl classroomDaoImpl;


    @Test
    public void testSaveAndGet() {
        Classroom classroom = Classroom.builder()
                .classroomId(CLASSROOM_ID)
                .userId(USER_ID)
                .title(ENGLISH)
                .session(PRESENT_SIMPLE)
                .description(THE_PRESENT_SIMPLE_TENSE)
                .code(CODE)
                .build();


        classroomDaoImpl.createClassroom(classroom);

        Classroom byId = classroomDaoImpl.getClassroomById(CLASSROOM_ID);
        assertNotNull(byId);
        assertEquals(CLASSROOM_ID, byId.getClassroomId());
        assertEquals(USER_ID,byId.getUserId());
        assertEquals(ENGLISH, byId.getTitle());
        assertEquals(PRESENT_SIMPLE, byId.getSession());
        assertEquals(THE_PRESENT_SIMPLE_TENSE, byId.getDescription());
        assertEquals(CODE, byId.getCode());

    }

    @Test
    public void Test() {


    }
}
