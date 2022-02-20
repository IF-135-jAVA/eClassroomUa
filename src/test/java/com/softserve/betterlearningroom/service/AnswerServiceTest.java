package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.AnswerDAO;
import com.softserve.betterlearningroom.dao.UserAssignmentDAO;
import com.softserve.betterlearningroom.dto.AnswerDTO;
import com.softserve.betterlearningroom.entity.Answer;
import com.softserve.betterlearningroom.entity.UserAssignment;
import com.softserve.betterlearningroom.exception.SubmissionNotAllowedException;
import com.softserve.betterlearningroom.service.impl.AnswerServiceImpl;
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
class AnswerServiceTest {

    private static final Long ID_1 = 1L;
    private static final Long USER_ASSIGNMENT_ID = 1L;
    private static final String TEXT_1 = "Monday";
    private static final boolean ENABLED = true;
    private static final Long ID_2 = 2L;
    private static final String TEXT_2 = "Tuesday";
    private static final Long MATERIAL_ID = 1L;
    private static final Long USER_ID = 1L;
    private static final Long ASSIGNMENT_STATUS_ID = 1L;
    private static final LocalDateTime SUBMISSION_DATE = LocalDateTime.now().minusMinutes(5);
    private static final LocalDateTime DUE_DATE_1 = LocalDateTime.now().plusDays(1);
    private static final LocalDateTime DUE_DATE_2 = LocalDateTime.now().minusDays(3);
    private static final String EXCEPTION_MESSAGE = "Due date for assignment with id - " + MATERIAL_ID + " has passed. Due date is " + DUE_DATE_2 + ".";
    private static final String MATERIAL_TITLE = "Assignment";
    private static final int MAX_SCORE = 12;
    private static final String USER_FIRST_NAME = "John";
    private static final String USER_LAST_NAME = "Doe";
    private static final String ASSIGNMENT_STATUS_TITLE = "IN_PROGRESS";

    private Answer answer1;
    private Answer answer1Updated;
    private Answer answer2;

    private AnswerDTO answerDTO1;
    private AnswerDTO answerDTO1Updated;
    private AnswerDTO answerDTO2;

    private UserAssignment userAssignment;

    @Mock
    private AnswerDAO answerDao;

    @Mock
    private UserAssignmentDAO userAssignmentDao;

    @InjectMocks
    private AnswerServiceImpl answerService;

    @BeforeEach
    void setUp() {
        answer1 = new Answer(ID_1, USER_ASSIGNMENT_ID, TEXT_1, ENABLED);
        answer1Updated = new Answer(ID_1, USER_ASSIGNMENT_ID, TEXT_2, ENABLED);
        answer2 = new Answer(ID_2, USER_ASSIGNMENT_ID, TEXT_2, ENABLED);
        answerDTO1 = new AnswerDTO(ID_1, USER_ASSIGNMENT_ID, TEXT_1, ENABLED);
        answerDTO1Updated = new AnswerDTO(ID_1, USER_ASSIGNMENT_ID, TEXT_2, ENABLED);
        answerDTO2 = new AnswerDTO(ID_2, USER_ASSIGNMENT_ID, TEXT_2, ENABLED);
        userAssignment = new UserAssignment(USER_ASSIGNMENT_ID, MATERIAL_ID, MATERIAL_TITLE, DUE_DATE_1, MAX_SCORE, USER_ID,
                USER_FIRST_NAME, USER_LAST_NAME, ASSIGNMENT_STATUS_ID, ASSIGNMENT_STATUS_TITLE, SUBMISSION_DATE, 0, null, ENABLED);
    }

    @Test
    void testSave() {
        when(answerDao.save(answer1)).thenReturn(answer1);
        when(userAssignmentDao.findById(USER_ASSIGNMENT_ID)).thenReturn(userAssignment);
        when(userAssignmentDao.update(userAssignment)).thenReturn(userAssignment);

        AnswerDTO actual = answerService.save(answerDTO1);

        verify(answerDao).save(any(Answer.class));
        verify(userAssignmentDao).findById(anyLong());
        verify(userAssignmentDao).update(any(UserAssignment.class));
        assertEquals(answerDTO1, actual);
        assertEquals(LocalDateTime.now().withNano(0).withSecond(0), userAssignment.getSubmissionDate().withNano(0).withSecond(0));
    }

    @Test
    void testSaveThrowsSubmissionNotAllowedException() {
        when(userAssignmentDao.findById(USER_ASSIGNMENT_ID)).thenReturn(userAssignment);
        userAssignment.setDueDate(DUE_DATE_2);

        SubmissionNotAllowedException exception = assertThrows(SubmissionNotAllowedException.class, () -> answerService.save(answerDTO1));

        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
        verifyNoInteractions(answerDao);
        verify(userAssignmentDao).findById(anyLong());
        verify(userAssignmentDao, times(0)).update(any(UserAssignment.class));
    }

    @Test
    void testFindById() {
        when(answerDao.findById(ID_1)).thenReturn(answer1);

        AnswerDTO actual = answerService.findById(ID_1);

        verify(answerDao).findById(anyLong());
        assertEquals(answerDTO1, actual);
    }

    @Test
    void testUpdate() {
        when(answerDao.update(answer1Updated)).thenReturn(answer1Updated);
        when(answerDao.findById(ID_1)).thenReturn(answer1);
        when(userAssignmentDao.findById(USER_ASSIGNMENT_ID)).thenReturn(userAssignment);
        when(userAssignmentDao.update(userAssignment)).thenReturn(userAssignment);

        AnswerDTO actual = answerService.update(answerDTO2, ID_1);

        verify(answerDao).update(any(Answer.class));
        verify(answerDao).findById(anyLong());
        verify(userAssignmentDao).findById(anyLong());
        verify(userAssignmentDao).update(any(UserAssignment.class));
        assertEquals(answerDTO1Updated, actual);
        assertEquals(LocalDateTime.now().withNano(0).withSecond(0), userAssignment.getSubmissionDate().withNano(0).withSecond(0));
    }

    @Test
    void testUpdateThrowsSubmissionNotAllowedException() {
        when(answerDao.findById(ID_1)).thenReturn(answer1);
        when(userAssignmentDao.findById(USER_ASSIGNMENT_ID)).thenReturn(userAssignment);
        userAssignment.setDueDate(DUE_DATE_2);

        SubmissionNotAllowedException exception = assertThrows(SubmissionNotAllowedException.class, () -> answerService.update(answerDTO2, ID_1));

        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
        verify(answerDao, times(0)).update(any(Answer.class));
        verify(answerDao).findById(anyLong());
        verify(userAssignmentDao).findById(anyLong());
        verify(userAssignmentDao, times(0)).update(any(UserAssignment.class));
    }

    @Test
    void testDelete() {
        when(answerDao.findById(ID_1)).thenReturn(answer1);
        when(userAssignmentDao.findById(USER_ASSIGNMENT_ID)).thenReturn(userAssignment);
        when(userAssignmentDao.update(userAssignment)).thenReturn(userAssignment);

        answerService.delete(ID_1);

        verify(answerDao).delete(anyLong());
        verify(answerDao).findById(anyLong());
        verify(userAssignmentDao).findById(anyLong());
        verify(userAssignmentDao).update(any(UserAssignment.class));
        assertEquals(LocalDateTime.now().withNano(0).withSecond(0), userAssignment.getSubmissionDate().withNano(0).withSecond(0));
    }

    @Test
    void testDeleteThrowsSubmissionNotAllowedException() {
        when(answerDao.findById(ID_1)).thenReturn(answer1);
        when(userAssignmentDao.findById(USER_ASSIGNMENT_ID)).thenReturn(userAssignment);
        userAssignment.setDueDate(DUE_DATE_2);

        SubmissionNotAllowedException exception = assertThrows(SubmissionNotAllowedException.class, () -> answerService.delete(ID_1));

        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
        verify(answerDao, times(0)).delete(anyLong());
        verify(answerDao).findById(anyLong());
        verify(userAssignmentDao).findById(anyLong());
        verify(userAssignmentDao, times(0)).update(any(UserAssignment.class));
    }

    @Test
    void testFindByUserAssignmentId() {
        when(answerDao.findByUserAssignmentId(USER_ASSIGNMENT_ID)).thenReturn(Arrays.asList(answer1, answer2));

        List<AnswerDTO> actual = answerService.findByUserAssignmentId(USER_ASSIGNMENT_ID);

        verify(answerDao).findByUserAssignmentId(anyLong());
        assertEquals(Arrays.asList(answerDTO1, answerDTO2), actual);
    }
}
