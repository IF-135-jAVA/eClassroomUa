//package com.softserve.betterlearningroom.service;
//
//import com.softserve.betterlearningroom.dao.impl.ClassroomDaoImpl;
//import com.softserve.betterlearningroom.dto.ClassroomDTO;
//import com.softserve.betterlearningroom.entity.Classroom;
//import com.softserve.betterlearningroom.service.impl.ClassroomServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(value = {MockitoExtension.class})
//public class ClassroomServiceTest {
//
//    @InjectMocks
//    private ClassroomServiceImpl classroomServiceImpl;
//
//    @Mock
//    private ClassroomDaoImpl classroomDaoImpl;
//    private ClassroomDTO expectedClassroomDTO;
//    private Classroom expectedClassroom;
//
//
//    @BeforeEach
//    void setUp() {
//        expectedClassroomDTO = ClassroomDTO.builder()
//                .classroomId(1L)
//                .userId(1L)
//                .title("English Language")
//                .session("Present Simple")
//                .description("The Present Simple Tense")
//                .code("3v8ev2t")
//                .build();
//        expectedClassroom = Classroom.builder()
//                .classroomId(1L)
//                .userId(1L)
//                .title("English Language")
//                .session("Present Simple")
//                .description("The Present Simple Tense")
//                .code("3v8ev2t")
//                .build();
//    }
//
//    @Test
//    public void testGetClassroomById() {
//        when(classroomDaoImpl.getClassroomById(1L)).thenReturn(expectedClassroom);
//
//        ClassroomDTO byId = classroomServiceImpl.getClassroomById(1L);
//
//        assertNotNull(byId);
//        assertEquals(expectedClassroom.getClassroomId(), byId.getClassroomId());
//        assertEquals(expectedClassroom.getUserId(), byId.getUserId());
//        assertEquals(expectedClassroom.getTitle(), byId.getTitle());
//        assertEquals(expectedClassroom.getSession(), byId.getSession());
//        assertEquals(expectedClassroom.getDescription(), byId.getDescription());
//        assertEquals(expectedClassroom.getCode(), byId.getCode());
//        verify(classroomDaoImpl).getClassroomOwnerById(1L);
//    }
//
//    @Test
//    public void testCreateClassroom() {
//
//    }
//
//    @Test
//    public void testGetClassroomOwnerById() {
//
//    }
//
//    @Test
//    public  void testGetClassroomTeachers(){
//
//    }
//
//    @Test
//    public void getClassroomStudents(){
//
//    }
//
//    @Test
//    public void getClassroomsByTeacher(){
//
//    }
//
//    @Test
//    public void getClassroomsByStudent(){
//
//    }
//
//    @Test
//    public void joinClassroomAsStudent(){
//
//    }
//
//    @Test
//    public void joinClassroomAsTeacher(){
//
//    }
//
//    @Test
//    public void testRemoveClassroomById(){
//
//    }
//}
