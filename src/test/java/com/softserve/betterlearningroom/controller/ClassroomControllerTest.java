//package com.softserve.betterlearningroom.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.softserve.betterlearningroom.dto.ClassroomDTO;
//import com.softserve.betterlearningroom.dto.UserDTO;
//import com.softserve.betterlearningroom.service.impl.ClassroomServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(value = ClassroomController.class, useDefaultFilters = false, includeFilters = {
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ClassroomController.class) })
//@AutoConfigureMockMvc(addFilters = false)
//class ClassroomControllerTest {
//
//    @Autowired
//    public MockMvc mockMvc;
//
//    @MockBean
//    private ClassroomServiceImpl classroomServiceImpl;
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    ClassroomDTO expectedClassroom() {
//        return ClassroomDTO.builder()
//                .classroomId(1L)
//                .userId(1L)
//                .title("Biology")
//                .session("Genetics")
//                .description("Genetics, Genomes, Chromosomes and the Cell Cycle")
//                .enabled(true)
//                .build();
//    }
//
//    UserDTO expectedUser() {
//        return UserDTO.builder()
//                .firstName("Yurii")
//                .lastName("Cheban")
//                .email("yuriicheban@gmail.com")
//                .enabled(true)
//                .build();
//    }
//
//    @Test
//    void testGetClassroomById() throws Exception {
//        when(classroomServiceImpl.findById(1L)).thenReturn(expectedClassroom());
//
//        MvcResult mvcResult = mockMvc.perform(get("/api/classrooms/1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn();
//        assertEquals(objectMapper.writeValueAsString(expectedClassroom()),
//                mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    void testGetClassroomOwnerById() throws Exception {
//        when(classroomServiceImpl.getClassroomOwnerById(1L)).thenReturn(expectedUser());
//
//        MvcResult mvcResult = mockMvc.perform(get("/api/classrooms/1/owner").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn();
//        assertEquals(objectMapper.writeValueAsString(expectedUser()), mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    void testGetClassroomTeachers() throws Exception {
//        List<UserDTO> expectedUsers = new ArrayList<>();
//        expectedUsers.add(expectedUser());
//
//        when(classroomServiceImpl.getClassroomTeachersById(1L)).thenReturn(expectedUsers);
//
//        MvcResult mvcResult = mockMvc.perform(get("/api/classrooms/1/teachers").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn();
//        assertEquals(objectMapper.writeValueAsString(expectedUsers), mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    void testGetClassroomStudents() throws Exception {
//        List<UserDTO> expectedUsers = new ArrayList<>();
//        expectedUsers.add(expectedUser());
//
//        when(classroomServiceImpl.getClassroomStudentsById(1L)).thenReturn(expectedUsers);
//
//        MvcResult mvcResult = mockMvc.perform(get("/api/classrooms/1/students").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn();
//        assertEquals(objectMapper.writeValueAsString(expectedUsers), mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    void testGetClassroomsByTeacher() throws Exception {
//        List<ClassroomDTO> expectedClassrooms = new ArrayList<>();
//        expectedClassrooms.add(expectedClassroom());
//
//        when(classroomServiceImpl.findAllClassroomsByTeacherId(1L)).thenReturn(expectedClassrooms);
//
//        MvcResult mvcResult = mockMvc
//                .perform(get("/api/classrooms/teacher/1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn();
//        assertEquals(objectMapper.writeValueAsString(expectedClassrooms), mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    void testGetClassroomsByStudent() throws Exception {
//        List<ClassroomDTO> expectedClassrooms = new ArrayList<>();
//        expectedClassrooms.add(expectedClassroom());
//
//        when(classroomServiceImpl.findAllClassroomsByStudentId(1L)).thenReturn(expectedClassrooms);
//
//        MvcResult mvcResult = mockMvc
//                .perform(get("/api/classrooms/student/1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn();
//        assertEquals(objectMapper.writeValueAsString(expectedClassrooms), mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    void testJoinClassroomAsStudent() throws Exception {
//        when(classroomServiceImpl.joinClassroomAsStudent(1L, 1L)).thenReturn(expectedClassroom());
//
//        MvcResult mvcResult = mockMvc
//                .perform(get("/api/classrooms/join/student?classroomId=1&userId=1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn();
//        assertEquals(objectMapper.writeValueAsString(expectedClassroom()),
//                mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    void testJoinClassroomAsTeacher() throws Exception {
//        when(classroomServiceImpl.joinClassroomAsTeacher(1L, 1L)).thenReturn(expectedClassroom());
//
//        MvcResult mvcResult = mockMvc
//                .perform(get("/api/classrooms/join/teacher?classroomId=1&userId=1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn();
//        assertEquals(objectMapper.writeValueAsString(expectedClassroom()),
//                mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    void testCreateClassroom() throws Exception {
//        ClassroomDTO classroomDTO = expectedClassroom();
//
//        when(classroomServiceImpl.save(classroomDTO)).thenReturn(expectedClassroom());
//        MvcResult mvcResult = mockMvc
//                .perform(post("/api/classrooms/").contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(classroomDTO)))
//                .andExpect(status().isCreated()).andReturn();
//
//        assertEquals(objectMapper.writeValueAsString(expectedClassroom()),
//                mvcResult.getResponse().getContentAsString());
//    }
//}
