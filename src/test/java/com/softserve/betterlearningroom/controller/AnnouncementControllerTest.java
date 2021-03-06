//package com.softserve.betterlearningroom.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.softserve.betterlearningroom.dto.AnnouncementDTO;
//import com.softserve.betterlearningroom.service.impl.AnnouncementServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.dao.DataRetrievalFailureException;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest(value = AnnouncementController.class, useDefaultFilters = false, includeFilters = {
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = AnnouncementController.class)})
//@AutoConfigureMockMvc(addFilters = false)
//class AnnouncementControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private AnnouncementServiceImpl announcementService;
//
//    private ObjectMapper mapper;
//
//    @Test
//    void readByAnnouncementIdTest() throws Exception {
//        mapper = new ObjectMapper();
//        AnnouncementDTO announcement = new AnnouncementDTO(1, 2, "text2", List.of(), true);
//        given(announcementService.findById(Mockito.anyLong())).willReturn(announcement);
//        mvc.perform(get("/api/classrooms/2/announcements/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//
//        verify(announcementService).findById(1L);
//    }
//
//    @Test
//    void createAnnouncementTest() throws Exception {
//        mapper = new ObjectMapper();
//        AnnouncementDTO announcement = new AnnouncementDTO(1, 2, "text2", List.of(), true);
//        given(announcementService.save(any(AnnouncementDTO.class))).willReturn(announcement);
//        mvc.perform(MockMvcRequestBuilders.post("/api/classrooms/2/announcements")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(announcement)))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    void updateAnnouncementTest() throws Exception {
//        mapper = new ObjectMapper();
//        AnnouncementDTO announcement = new AnnouncementDTO(1, 2, "text2", List.of(), true);
//        given(announcementService.update(any(AnnouncementDTO.class), anyLong())).willReturn(announcement);
//        mvc.perform(put("/api/classrooms/2/announcements/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(announcement)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//        verify(announcementService).update(any(AnnouncementDTO.class), anyLong());
//    }
//
//    @Test
//    void readByCourseIdTest() throws Exception {
//        List<AnnouncementDTO> announcementList = new ArrayList<>();
//        announcementList.add(new AnnouncementDTO(1, 2, "text1", List.of(), true));
//        announcementList.add(new AnnouncementDTO(2, 1, "text2", List.of(), true));
//        given(announcementService.findByCourseId(anyLong())).willReturn(announcementList);
//        mvc.perform(get("/api/classrooms/1/announcements")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    void announcementIsNotFoundTest() throws Exception {
//        given(announcementService.findById(Mockito.anyLong())).willThrow(DataRetrievalFailureException.class);
//        assertThatThrownBy(() -> mvc.perform(get("/api/classrooms/1/announcements/0")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).hasCause(new DataRetrievalFailureException(null));
//        verify(announcementService).findById(0L);
//    }
//}