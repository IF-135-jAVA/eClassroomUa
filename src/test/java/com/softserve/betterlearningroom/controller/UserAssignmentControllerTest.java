package com.softserve.betterlearningroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softserve.betterlearningroom.dto.UserAssignmentDTO;
import com.softserve.betterlearningroom.service.UserAssignmentService;
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

import java.time.LocalDateTime;
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

@WebMvcTest(value = UserAssignmentController.class, useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = UserAssignmentController.class)})
@AutoConfigureMockMvc(addFilters = false)
public class UserAssignmentControllerTest {

    private static final Long ID_1 = 1L;
    private static final Long MATERIAL_ID = 1L;
    private static final Long USER_ID_1 = 2L;
    private static final Long ASSIGNMENT_STATUS_ID_1 = 1L;
    private static final LocalDateTime SUBMISSION_DATE_1 = null;
    private static final int GRADE_1 = 0;
    private static final String FEEDBACK_1 = null;
    private static final boolean ENABLED = true;

    private static final Long ID_2 = 2L;
    private static final Long USER_ID_2 = 3L;
    private static final Long ASSIGNMENT_STATUS_ID_2 = 3L;
    private static final LocalDateTime SUBMISSION_DATE_2 = LocalDateTime.now().minusDays(2);
    private static final int GRADE_2 = 9;
    private static final String FEEDBACK_2 = "Almost good";

    private UserAssignmentDTO userAssignmentDTO1;
    private UserAssignmentDTO userAssignmentDTO1Updated;
    private UserAssignmentDTO userAssignmentDTO2;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserAssignmentService userAssignmentService;

    @BeforeEach
    public void setUp() {
        userAssignmentDTO1 = new UserAssignmentDTO(ID_1, MATERIAL_ID, USER_ID_1, ASSIGNMENT_STATUS_ID_1, SUBMISSION_DATE_1, GRADE_1, FEEDBACK_1, ENABLED);
        userAssignmentDTO1Updated = new UserAssignmentDTO(ID_1, MATERIAL_ID, USER_ID_1, ASSIGNMENT_STATUS_ID_2, SUBMISSION_DATE_1, GRADE_2, FEEDBACK_2, ENABLED);
        userAssignmentDTO2 = new UserAssignmentDTO(ID_2, MATERIAL_ID, USER_ID_2, ASSIGNMENT_STATUS_ID_2, SUBMISSION_DATE_2, GRADE_2, FEEDBACK_2, ENABLED);

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void testSave() throws Exception {
        when(userAssignmentService.save(userAssignmentDTO1)).thenReturn(userAssignmentDTO1);

        MvcResult mvcResult = mockMvc.perform(post("/api/materials/" + MATERIAL_ID + "/assignments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userAssignmentDTO1)))
                .andExpect(status().isCreated())
                .andReturn();

        verify(userAssignmentService).save(any(UserAssignmentDTO.class));
        assertEquals(objectMapper.writeValueAsString(userAssignmentDTO1), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testFindById() throws Exception {
        when(userAssignmentService.findById(ID_2)).thenReturn(userAssignmentDTO2);

        MvcResult mvcResult = mockMvc.perform(get("/api/materials/" + MATERIAL_ID + "/assignments/" + ID_2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(userAssignmentService).findById(anyLong());
        assertEquals(objectMapper.writeValueAsString(userAssignmentDTO2), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testUpdate() throws Exception {
        when(userAssignmentService.update(userAssignmentDTO2, ID_1)).thenReturn(userAssignmentDTO1Updated);

        MvcResult mvcResult = mockMvc.perform(put("/api/materials/" + MATERIAL_ID + "/assignments/" + ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userAssignmentDTO2)))
                .andExpect(status().isOk())
                .andReturn();

        verify(userAssignmentService).update(any(UserAssignmentDTO.class), anyLong());
        assertEquals(objectMapper.writeValueAsString(userAssignmentDTO1Updated), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testDelete() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/api/materials/" + MATERIAL_ID + "/assignments/" + ID_2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        verify(userAssignmentService).delete(anyLong());
        assertEquals("", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testFindByAssignmentId() throws Exception {
        when(userAssignmentService.findByAssignmentId(MATERIAL_ID)).thenReturn(Arrays.asList(userAssignmentDTO1, userAssignmentDTO2));

        MvcResult mvcResult = mockMvc.perform(get("/api/materials/" + MATERIAL_ID + "/assignments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(userAssignmentService).findByAssignmentId(anyLong());
        assertEquals(objectMapper.writeValueAsString(Arrays.asList(userAssignmentDTO1, userAssignmentDTO2)), mvcResult.getResponse().getContentAsString());
    }
}
