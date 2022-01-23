//package com.softserve.betterlearningroom.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.softserve.betterlearningroom.dto.LevelDTO;
//import com.softserve.betterlearningroom.service.impl.LevelServiceImpl;
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
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(
//        value = LevelController.class
//        , useDefaultFilters = false
//        , includeFilters = {
//        @ComponentScan.Filter(
//                type = FilterType.ASSIGNABLE_TYPE,
//                value = LevelController.class
//        )
//}
//)
//
//@AutoConfigureMockMvc(addFilters = false)
//class LevelControllerTest {
//
//    @Autowired
//    public MockMvc mockMvc;
//
//    @MockBean
//    LevelServiceImpl levelServiceImpl;
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    private LevelDTO testLevel() {
//        return LevelDTO.builder()
//                .id(1L)
//                .title("Pythagorean theorem")
//                .description("Write example")
//                .criterionId(2L)
//                .mark(5)
//                .build();
//    }
//
//    @Test
//    void testGetById() throws Exception {
//        when(levelServiceImpl.findById(1L)).thenReturn(testLevel());
//
//        MvcResult mvcResult = mockMvc.perform(get("/api/classrooms/1/topics/1/materials/2/criterions/1/level/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//        assertEquals(objectMapper.writeValueAsString(testLevel()), mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    void testSave() throws Exception {
//        LevelDTO LevelDTO = testLevel();
//
//        when(levelServiceImpl.save(LevelDTO)).thenReturn(testLevel());
//        MvcResult mvcResult = mockMvc.perform(post("/api/classrooms/1/topics/1/materials/2/criterions/1/level")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsBytes(LevelDTO)))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        assertEquals(objectMapper.writeValueAsString(testLevel()),
//                mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    void testPut() throws Exception {
//        LevelDTO LevelDTO = testLevel();
//
//        when(levelServiceImpl.update(LevelDTO)).thenReturn(testLevel());
//        MvcResult mvcResult = mockMvc.perform(put("/api/classrooms/1/topics/1/materials/2/criterions/1/level")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsBytes(LevelDTO)))
//                .andExpect(status().isAccepted())
//                .andReturn();
//        System.out.println("res" + mvcResult.getResponse().getContentAsString());
//
//        assertEquals(objectMapper.writeValueAsString(testLevel()),
//                mvcResult.getResponse().getContentAsString());
//    }
//
//}