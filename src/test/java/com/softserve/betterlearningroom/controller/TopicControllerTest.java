//package com.softserve.betterlearningroom.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.softserve.betterlearningroom.dto.TopicDTO;
//import com.softserve.betterlearningroom.service.impl.TopicServiceImpl;
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
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(
//        value = TopicController.class
//        , useDefaultFilters = false
//        , includeFilters = {
//        @ComponentScan.Filter(
//                type = FilterType.ASSIGNABLE_TYPE,
//                value = TopicController.class
//        )
//}
//)
//
//@AutoConfigureMockMvc(addFilters = false)
//class TopicControllerTest {
//
//    @Autowired
//    public MockMvc mockMvc;
//
//    @MockBean
//    private TopicServiceImpl topicServiceImpl;
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    private TopicDTO testTopic() {
//        return TopicDTO.builder()
//                .id(1L)
//                .classroomId(2L)
//                .title("Mathematics")
//                .build();
//    }
//
//    @Test
//    void testGetById() throws Exception {
//        when(topicServiceImpl.findById(1L)).thenReturn(testTopic());
//
//        MvcResult mvcResult = mockMvc.perform(get("/api/classrooms/1/topics/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//        assertEquals(objectMapper.writeValueAsString(testTopic()), mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    void testSave() throws Exception {
//        TopicDTO topicDTO = testTopic();
//
//        when(topicServiceImpl.save(topicDTO)).thenReturn(testTopic());
//        MvcResult mvcResult = mockMvc.perform(post("/api/classrooms/1/topics")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsBytes(topicDTO)))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        assertEquals(objectMapper.writeValueAsString(testTopic()),
//                mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    void testPut() throws Exception {
//        TopicDTO topicDTO = testTopic();
//
//        when(topicServiceImpl.update(topicDTO)).thenReturn(testTopic());
//        MvcResult mvcResult = mockMvc.perform(put("/api/classrooms/1/topics")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsBytes(topicDTO)))
//                .andExpect(status().isAccepted())
//                .andReturn();
//        System.out.println("res" + mvcResult.getResponse().getContentAsString());
//
//        assertEquals(objectMapper.writeValueAsString(testTopic()),
//                mvcResult.getResponse().getContentAsString());
//    }
//}