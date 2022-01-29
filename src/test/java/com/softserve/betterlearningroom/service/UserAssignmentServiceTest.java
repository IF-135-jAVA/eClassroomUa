package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.AnswerDAO;
import com.softserve.betterlearningroom.dao.MaterialDAO;
import com.softserve.betterlearningroom.dao.UserAssignmentDAO;
import com.softserve.betterlearningroom.dto.UserAssignmentDTO;
import com.softserve.betterlearningroom.entity.Answer;
import com.softserve.betterlearningroom.entity.Assignment;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.UserAssignment;
import com.softserve.betterlearningroom.exception.SubmissionNotAllowedException;
import com.softserve.betterlearningroom.service.impl.UserAssignmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAssignmentServiceTest {

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
    private static final LocalDateTime DUE_DATE_1 = LocalDateTime.now().plusDays(1);
    private static final LocalDateTime DUE_DATE_2 = LocalDateTime.now().minusDays(3);
    private static final Long ANSWER_ID_1 = 1L;
    private static final Long ANSWER_ID_2 = 2L;
    private static final String EXCEPTION_MESSAGE = "Due date for assignment with id - " + MATERIAL_ID + " has passed. Due date is " + DUE_DATE_2 + ".";

    private UserAssignment userAssignment1;
    private UserAssignment userAssignment1Updated;
    private UserAssignment userAssignment2;

    private UserAssignmentDTO userAssignmentDTO1;
    private UserAssignmentDTO userAssignmentDTO1Updated;
    private UserAssignmentDTO userAssignmentDTO2;

    private Material material;

    private Answer answer1;
    private Answer answer2;

    @Mock
    private UserAssignmentDAO userAssignmentDao;

    @Mock
    private AnswerDAO answerDao;

    @Mock
    private MaterialDAO materialDao;

    @InjectMocks
    private UserAssignmentServiceImpl userAssignmentService;

    @BeforeEach
    void setUp() {
        userAssignment1 = new UserAssignment(ID_1, MATERIAL_ID, USER_ID_1, ASSIGNMENT_STATUS_ID_1, SUBMISSION_DATE_1, GRADE_1, FEEDBACK_1, ENABLED);
        userAssignment1Updated = new UserAssignment(ID_1, MATERIAL_ID, USER_ID_1, ASSIGNMENT_STATUS_ID_2, SUBMISSION_DATE_1, GRADE_2, FEEDBACK_2, ENABLED);
        userAssignment2 = new UserAssignment(ID_2, MATERIAL_ID, USER_ID_2, ASSIGNMENT_STATUS_ID_2, SUBMISSION_DATE_2, GRADE_2, FEEDBACK_2, ENABLED);
        userAssignmentDTO1 = new UserAssignmentDTO(ID_1, MATERIAL_ID, USER_ID_1, ASSIGNMENT_STATUS_ID_1, SUBMISSION_DATE_1, GRADE_1, FEEDBACK_1, ENABLED);
        userAssignmentDTO1Updated = new UserAssignmentDTO(ID_1, MATERIAL_ID, USER_ID_1, ASSIGNMENT_STATUS_ID_2, SUBMISSION_DATE_1, GRADE_2, FEEDBACK_2, ENABLED);
        userAssignmentDTO2 = new UserAssignmentDTO(ID_2, MATERIAL_ID, USER_ID_2, ASSIGNMENT_STATUS_ID_2, SUBMISSION_DATE_2, GRADE_2, FEEDBACK_2, ENABLED);
        material = new Assignment();
        material.setId(MATERIAL_ID);
        material.setDueDate(DUE_DATE_1);
        answer1 = new Answer(ANSWER_ID_1, ID_2, null, ENABLED);
        answer2 = new Answer(ANSWER_ID_2, ID_2, null, ENABLED);
    }

    @Test
    void testSave() {
        when(userAssignmentDao.save(userAssignment1)).thenReturn(userAssignment1);
        when(materialDao.findById(MATERIAL_ID)).thenReturn(material);

        UserAssignmentDTO actual = userAssignmentService.save(userAssignmentDTO1);

        verify(userAssignmentDao).save(any(UserAssignment.class));
        verify(materialDao).findById(anyLong());
        assertEquals(userAssignmentDTO1, actual);
    }

    @Test
    void testSaveThrowsSubmissionNotAllowedException() {
        when(materialDao.findById(MATERIAL_ID)).thenReturn(material);
        material.setDueDate(DUE_DATE_2);

        SubmissionNotAllowedException exception = assertThrows(SubmissionNotAllowedException.class, () -> userAssignmentService.save(userAssignmentDTO1));

        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
        verifyNoInteractions(userAssignmentDao);
        verify(materialDao).findById(anyLong());
    }

    @Test
    void testFindById() {
        when(userAssignmentDao.findById(ID_2)).thenReturn(userAssignment2);

        UserAssignmentDTO actual = userAssignmentService.findById(ID_2);

        verify(userAssignmentDao).findById(anyLong());
        assertEquals(userAssignmentDTO2, actual);
    }

    @Test
    void testUpdate() {
        when(userAssignmentDao.update(userAssignment1Updated)).thenReturn(userAssignment1Updated);
        when(userAssignmentDao.findById(ID_1)).thenReturn(userAssignment1);

        UserAssignmentDTO actual = userAssignmentService.update(userAssignmentDTO2, ID_1);

        verify(userAssignmentDao).update(any(UserAssignment.class));
        verify(userAssignmentDao).findById(anyLong());
        assertEquals(userAssignmentDTO1Updated, actual);
    }

    @Test
    void testDelete() {
        List<Answer> answers = Arrays.asList(answer1, answer2);
        when(answerDao.findByUserAssignmentId(ID_2)).thenReturn(answers);

        userAssignmentService.delete(ID_2);

        verify(userAssignmentDao).delete(anyLong());
        verify(answerDao).findByUserAssignmentId(anyLong());
        verify(answerDao, times(answers.size())).delete(anyLong());
    }

    @Test
    void testFindByAssignmentId() {
        when(userAssignmentDao.findByAssignmentId(MATERIAL_ID)).thenReturn(Arrays.asList(userAssignment1, userAssignment2));

        List<UserAssignmentDTO> actual = userAssignmentService.findByAssignmentId(MATERIAL_ID);

        verify(userAssignmentDao).findByAssignmentId(anyLong());
        assertEquals(Arrays.asList(userAssignmentDTO1, userAssignmentDTO2), actual);
    }
}
