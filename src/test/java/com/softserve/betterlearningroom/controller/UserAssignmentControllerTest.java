package com.softserve.betterlearningroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softserve.betterlearningroom.dto.UserAssignmentDTO;
import com.softserve.betterlearningroom.dto.UserAssignmentEvaluationDTO;
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
class UserAssignmentControllerTest {

    private static final Long ID_1 = 1L;
    private static final Long MATERIAL_ID = 1L;
    private static final Long USER_ID_1 = 2L;
    private static final Long ASSIGNMENT_STATUS_ID_1 = 1L;
    private static final LocalDateTime SUBMISSION_DATE_1 = null;
    private static final int GRADE_1 = 0;
    private static final String FEEDBACK_1 = null;
    private static final boolean ENABLED = true;
    private static final String MATERIAL_TITLE = "Assignment";
    private static final LocalDateTime DUE_DATE = LocalDateTime.now().plusDays(1);
    private static final int MAX_SCORE = 12;
    private static final String USER_FIRST_NAME_1 = "John";
    private static final String USER_LAST_NAME_1 = "Doe";
    private static final String ASSIGNMENT_STATUS_TITLE_1 = "IN_PROGRESS";

    private static final Long ID_2 = 2L;
    private static final Long USER_ID_2 = 3L;
    private static final Long ASSIGNMENT_STATUS_ID_2 = 2L;
    private static final LocalDateTime SUBMISSION_DATE_2 = LocalDateTime.now().minusDays(2);
    private static final int GRADE_2 = 9;
    private static final String FEEDBACK_2 = "Almost good";
    private static final String USER_FIRST_NAME_2 = "Jack";
    private static final String USER_LAST_NAME_2 = "Old";
    private static final String ASSIGNMENT_STATUS_TITLE_2 = "REVIEWED";
    private static final Long ASSIGNMENT_STATUS_ID_3 = 3L;
    private static final String ASSIGNMENT_STATUS_TITLE_3 = "DONE";

    private UserAssignmentDTO userAssignmentDTO1BeforeSaving;
    private UserAssignmentDTO userAssignmentDTO1;
    private UserAssignmentEvaluationDTO userAssignmentEvaluationDTO;
    private UserAssignmentDTO userAssignmentDTO1UpdatedByTeacher;
    private UserAssignmentDTO userAssignmentDTO1UpdatedByStudent;
    private UserAssignmentDTO userAssignmentDTO2;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserAssignmentService userAssignmentService;

    @BeforeEach
    void setUp() {
        userAssignmentDTO1BeforeSaving = new UserAssignmentDTO(ID_1, MATERIAL_ID, null, null, 0, USER_ID_1, null, null, ASSIGNMENT_STATUS_ID_1, null, SUBMISSION_DATE_1, GRADE_1, FEEDBACK_1, ENABLED);
        userAssignmentDTO1 = new UserAssignmentDTO(ID_1, MATERIAL_ID, MATERIAL_TITLE, DUE_DATE, MAX_SCORE, USER_ID_1,
                USER_FIRST_NAME_1, USER_LAST_NAME_1, ASSIGNMENT_STATUS_ID_1, ASSIGNMENT_STATUS_TITLE_1, SUBMISSION_DATE_1, GRADE_1, FEEDBACK_1, ENABLED);
        userAssignmentEvaluationDTO = new UserAssignmentEvaluationDTO(GRADE_2, FEEDBACK_2);
        userAssignmentDTO1UpdatedByTeacher = new UserAssignmentDTO(ID_1, MATERIAL_ID, MATERIAL_TITLE, DUE_DATE, MAX_SCORE, USER_ID_1,
                USER_FIRST_NAME_1, USER_LAST_NAME_1, ASSIGNMENT_STATUS_ID_2, ASSIGNMENT_STATUS_TITLE_2, SUBMISSION_DATE_1, GRADE_2, FEEDBACK_2, ENABLED);
        userAssignmentDTO1UpdatedByStudent = new UserAssignmentDTO(ID_1, MATERIAL_ID, MATERIAL_TITLE, DUE_DATE, MAX_SCORE, USER_ID_1,
                USER_FIRST_NAME_1, USER_LAST_NAME_1, ASSIGNMENT_STATUS_ID_3, ASSIGNMENT_STATUS_TITLE_3, SUBMISSION_DATE_1, GRADE_1, FEEDBACK_1, ENABLED);
        userAssignmentDTO2 = new UserAssignmentDTO(ID_2, MATERIAL_ID, MATERIAL_TITLE, DUE_DATE, MAX_SCORE, USER_ID_2,
                USER_FIRST_NAME_2, USER_LAST_NAME_2, ASSIGNMENT_STATUS_ID_3, ASSIGNMENT_STATUS_TITLE_3, SUBMISSION_DATE_2, GRADE_2, FEEDBACK_2, ENABLED);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void testSave() throws Exception {
        when(userAssignmentService.save(userAssignmentDTO1BeforeSaving)).thenReturn(userAssignmentDTO1);

        MvcResult mvcResult = mockMvc.perform(post("/api/materials/" + MATERIAL_ID + "/assignments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userAssignmentDTO1BeforeSaving)))
                .andExpect(status().isCreated())
                .andReturn();

        verify(userAssignmentService).save(any(UserAssignmentDTO.class));
        assertEquals(objectMapper.writeValueAsString(userAssignmentDTO1), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testFindById() throws Exception {
        when(userAssignmentService.findById(ID_2)).thenReturn(userAssignmentDTO2);

        MvcResult mvcResult = mockMvc.perform(get("/api/materials/" + MATERIAL_ID + "/assignments/" + ID_2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(userAssignmentService).findById(anyLong());
        assertEquals(objectMapper.writeValueAsString(userAssignmentDTO2), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testUpdateAsTeacher() throws Exception {
        when(userAssignmentService.updateAsTeacher(userAssignmentEvaluationDTO, ID_1)).thenReturn(userAssignmentDTO1UpdatedByTeacher);

        MvcResult mvcResult = mockMvc.perform(put("/api/materials/" + MATERIAL_ID + "/assignments/" + ID_1 + "/evaluate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userAssignmentEvaluationDTO)))
                .andExpect(status().isOk())
                .andReturn();

        verify(userAssignmentService).updateAsTeacher(any(UserAssignmentEvaluationDTO.class), anyLong());
        assertEquals(objectMapper.writeValueAsString(userAssignmentDTO1UpdatedByTeacher), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testUpdateAsStudent() throws Exception {
        when(userAssignmentService.updateAsStudent(userAssignmentDTO2, ID_1)).thenReturn(userAssignmentDTO1UpdatedByStudent);

        MvcResult mvcResult = mockMvc.perform(put("/api/materials/" + MATERIAL_ID + "/assignments/" + ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userAssignmentDTO2)))
                .andExpect(status().isOk())
                .andReturn();

        verify(userAssignmentService).updateAsStudent(any(UserAssignmentDTO.class), anyLong());
        assertEquals(objectMapper.writeValueAsString(userAssignmentDTO1UpdatedByStudent), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testDelete() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/api/materials/" + MATERIAL_ID + "/assignments/" + ID_2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        verify(userAssignmentService).delete(anyLong());
        assertEquals("", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testFindByAssignmentId() throws Exception {
        when(userAssignmentService.findByAssignmentId(MATERIAL_ID)).thenReturn(Arrays.asList(userAssignmentDTO1, userAssignmentDTO2));

        MvcResult mvcResult = mockMvc.perform(get("/api/materials/" + MATERIAL_ID + "/assignments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(userAssignmentService).findByAssignmentId(anyLong());
        assertEquals(objectMapper.writeValueAsString(Arrays.asList(userAssignmentDTO1, userAssignmentDTO2)), mvcResult.getResponse().getContentAsString());
    }
}
