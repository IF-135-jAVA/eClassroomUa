package com.softserve.betterlearningroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.service.impl.CriterionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CriterionController.class, useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CriterionController.class) })

@AutoConfigureMockMvc(addFilters = false)
class CriterionControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    private CriterionServiceImpl criterionServiceImpl;
    private ObjectMapper objectMapper = new ObjectMapper();

    private CriterionDTO testCriterion() {
        return CriterionDTO.builder().id(1L).materialIdDTO(2L).title("Use formula").description("Using wright formula")
                .build();
    }

    @Test
    void testGetById() throws Exception {
        when(criterionServiceImpl.findById(1L)).thenReturn(testCriterion());

        MvcResult mvcResult = mockMvc.perform(
                get("/api/classrooms/1/topics/1/materials/2/criterions/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertEquals(objectMapper.writeValueAsString(testCriterion()), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testSave() throws Exception {
        CriterionDTO criterionDTO = testCriterion();

        when(criterionServiceImpl.save(criterionDTO)).thenReturn(testCriterion());
        MvcResult mvcResult = mockMvc
                .perform(post("/api/classrooms/1/topics/1/materials/2/criterions")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(criterionDTO)))
                .andExpect(status().isCreated()).andReturn();

        assertEquals(objectMapper.writeValueAsString(testCriterion()), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testPut() throws Exception {
        CriterionDTO criterionDTO = testCriterion();

        when(criterionServiceImpl.update(criterionDTO)).thenReturn(testCriterion());
        MvcResult mvcResult = mockMvc
                .perform(put("/api/classrooms/1/topics/1/materials/2/criterions")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(criterionDTO)))
                .andExpect(status().isAccepted()).andReturn();

        assertEquals(objectMapper.writeValueAsString(testCriterion()), mvcResult.getResponse().getContentAsString());
    }
}