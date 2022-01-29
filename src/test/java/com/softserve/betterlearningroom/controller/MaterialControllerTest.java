package com.softserve.betterlearningroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.service.impl.MaterialServiceImpl;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CriterionController.class, useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MaterialController.class) })

@AutoConfigureMockMvc(addFilters = false)
class MaterialControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    private MaterialServiceImpl materialServiceImpl;
    private ObjectMapper objectMapper = new ObjectMapper();

    private MaterialDTO testMaterial(){
        return MaterialDTO.builder()
                .id(1L)
                .title("title")
                .text("text")
                .classroomId(1L)
                .build();

    }
    @Test
    void testGetById() throws Exception {
        when(materialServiceImpl.findMaterialById(1L)).thenReturn(testMaterial());

        MvcResult mvcResult = mockMvc.perform(get("/api/classrooms/1/topics/1/materials/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertEquals(objectMapper.writeValueAsString(testMaterial()), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testSave() throws Exception {
        MaterialDTO materialDTO = testMaterial();

        when(materialServiceImpl.save(materialDTO, 1L)).thenReturn(testMaterial());
        MvcResult mvcResult = mockMvc
                .perform(post("/api/classrooms/1/topics/1/materials/")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(materialDTO)))
                .andExpect(status().isCreated()).andReturn();

        assertEquals(objectMapper.writeValueAsString(testMaterial()), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testPut() throws Exception {
        MaterialDTO materialDTO = testMaterial();

        when(materialServiceImpl.update(materialDTO)).thenReturn(testMaterial());
        MvcResult mvcResult = mockMvc
                .perform(put("/api/classrooms/1/topics/1/materials/1")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(materialDTO)))
                .andExpect(status().isAccepted()).andReturn();

        assertEquals(objectMapper.writeValueAsString(testMaterial()), mvcResult.getResponse().getContentAsString());
    }



}