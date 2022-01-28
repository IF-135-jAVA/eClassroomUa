package com.softserve.betterlearningroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.betterlearningroom.dto.AnswerDTO;
import com.softserve.betterlearningroom.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AnswerController.class, useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = AnswerController.class)})
@AutoConfigureMockMvc(addFilters = false)
public class AnswerControllerTest {

    private static final Long ID_1 = 1L;
    private static final Long USER_ASSIGNMENT_ID = 1L;
    private static final String TEXT_1 = "Monday";
    private static final boolean ENABLED = true;

    private static final Long ID_2 = 2L;
    private static final String TEXT_2 = "Tuesday";

    private AnswerDTO answerDTO1;
    private AnswerDTO answerDTO1Updated;
    private AnswerDTO answerDTO2;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService answerService;

    @BeforeEach
    public void setUp() {
        answerDTO1 = new AnswerDTO(ID_1, USER_ASSIGNMENT_ID, TEXT_1, ENABLED);
        answerDTO1Updated = new AnswerDTO(ID_1, USER_ASSIGNMENT_ID, TEXT_2, ENABLED);
        answerDTO2 = new AnswerDTO(ID_2, USER_ASSIGNMENT_ID, TEXT_2, ENABLED);
    }

    @Test
    public void testSave() throws Exception {
        when(answerService.save(answerDTO1)).thenReturn(answerDTO1);

        MvcResult mvcResult = mockMvc.perform(post("/api/assignments/" + USER_ASSIGNMENT_ID + "/answers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(answerDTO1)))
                .andExpect(status().isCreated())
                .andReturn();

        verify(answerService).save(any(AnswerDTO.class));
        assertEquals(objectMapper.writeValueAsString(answerDTO1), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testFindById() throws Exception {
        when(answerService.findById(ID_1)).thenReturn(answerDTO1);

        MvcResult mvcResult = mockMvc.perform(get("/api/assignments/" + USER_ASSIGNMENT_ID + "/answers/" + ID_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(answerService).findById(anyLong());
        assertEquals(objectMapper.writeValueAsString(answerDTO1), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testUpdate() throws Exception {
        when(answerService.update(answerDTO2, ID_1)).thenReturn(answerDTO1Updated);

        MvcResult mvcResult = mockMvc.perform(put("/api/assignments/" + USER_ASSIGNMENT_ID + "/answers/" + ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(answerDTO2)))
                .andExpect(status().isOk())
                .andReturn();

        verify(answerService).update(any(AnswerDTO.class), anyLong());
        assertEquals(objectMapper.writeValueAsString(answerDTO1Updated), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testDelete() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/api/assignments/" + USER_ASSIGNMENT_ID + "/answers/" + ID_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        verify(answerService).delete(anyLong());
        assertEquals("", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testFindByUserAssignmentId() throws Exception {
        when(answerService.findByUserAssignmentId(USER_ASSIGNMENT_ID)).thenReturn(Arrays.asList(answerDTO1, answerDTO2));

        MvcResult mvcResult = mockMvc.perform(get("/api/assignments/" + USER_ASSIGNMENT_ID + "/answers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(answerService).findByUserAssignmentId(anyLong());
        assertEquals(objectMapper.writeValueAsString(Arrays.asList(answerDTO1, answerDTO2)), mvcResult.getResponse().getContentAsString());
    }
}
