package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.AnswerDao;
import com.softserve.betterlearningroom.dao.MaterialDao;
import com.softserve.betterlearningroom.dao.UserAssignmentDao;
import com.softserve.betterlearningroom.dto.AnswerDTO;
import com.softserve.betterlearningroom.entity.Answer;
import com.softserve.betterlearningroom.entity.Assignment;
import com.softserve.betterlearningroom.entity.Material;
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
public class AnswerServiceTest {

    private static final long ID_1 = 1;
    private static final long USER_ASSIGNMENT_ID = 1;
    private static final String TEXT_1 = "Monday";
    private static final boolean ENABLED = true;

    private static final long ID_2 = 2;
    private static final String TEXT_2 = "Tuesday";

    private static final long MATERIAL_ID = 1;
    private static final long USER_ID = 1;
    private static final long ASSIGNMENT_STATUS_ID = 1;
    private static final LocalDateTime SUBMISSION_DATE = LocalDateTime.now().minusMinutes(5);

    private static final LocalDateTime DUE_DATE_1 = LocalDateTime.now().plusDays(1);

    private static final LocalDateTime DUE_DATE_2 = LocalDateTime.now().minusDays(3);

    private static final String EXCEPTION_MESSAGE = "Due date for assignment with id - " + MATERIAL_ID + " has passed. Due date is " + DUE_DATE_2 + ".";

    private Answer answer1;
    private Answer answer1Updated;
    private Answer answer2;

    private AnswerDTO answerDTO1;
    private AnswerDTO answerDTO1Updated;
    private AnswerDTO answerDTO2;

    private UserAssignment userAssignment;

    private Material material;

    @Mock
    private AnswerDao answerDao;

    @Mock
    private UserAssignmentDao userAssignmentDao;

    @Mock
    private MaterialDao materialDao;

    @InjectMocks
    private AnswerServiceImpl answerService;

    @BeforeEach
    public void setUp() {
        answer1 = new Answer(ID_1, USER_ASSIGNMENT_ID, TEXT_1, ENABLED);
        answer1Updated = new Answer(ID_1, USER_ASSIGNMENT_ID, TEXT_2, ENABLED);
        answer2 = new Answer(ID_2, USER_ASSIGNMENT_ID, TEXT_2, ENABLED);
        answerDTO1 = new AnswerDTO(ID_1, USER_ASSIGNMENT_ID, TEXT_1, ENABLED);
        answerDTO1Updated = new AnswerDTO(ID_1, USER_ASSIGNMENT_ID, TEXT_2, ENABLED);
        answerDTO2 = new AnswerDTO(ID_2, USER_ASSIGNMENT_ID, TEXT_2, ENABLED);
        userAssignment = new UserAssignment(USER_ASSIGNMENT_ID, MATERIAL_ID, USER_ID, ASSIGNMENT_STATUS_ID, SUBMISSION_DATE, 0, null, ENABLED);
        material = new Assignment();
        material.setId(MATERIAL_ID);
        material.setDueDate(DUE_DATE_1);
    }

    @Test
    public void testCreate() {
        when(answerDao.create(answer1)).thenReturn(answer1);
        when(userAssignmentDao.readById(USER_ASSIGNMENT_ID)).thenReturn(userAssignment);
        when(materialDao.getById(MATERIAL_ID)).thenReturn(material);
        when(userAssignmentDao.update(userAssignment)).thenReturn(userAssignment);

        AnswerDTO actual = answerService.create(answerDTO1);

        verify(answerDao).create(any(Answer.class));
        verify(userAssignmentDao).readById(anyLong());
        verify(materialDao).getById(anyLong());
        verify(userAssignmentDao).update(any(UserAssignment.class));
        assertEquals(answerDTO1, actual);
        assertEquals(LocalDateTime.now().withNano(0).withSecond(0), userAssignment.getSubmissionDate().withNano(0).withSecond(0));
    }

    @Test
    public void testCreateThrowsSubmissionNotAllowedException() {
        when(userAssignmentDao.readById(USER_ASSIGNMENT_ID)).thenReturn(userAssignment);
        when(materialDao.getById(MATERIAL_ID)).thenReturn(material);
        material.setDueDate(DUE_DATE_2);

        SubmissionNotAllowedException exception = assertThrows(SubmissionNotAllowedException.class, () -> answerService.create(answerDTO1));

        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
        verifyNoInteractions(answerDao);
        verify(userAssignmentDao).readById(anyLong());
        verify(materialDao).getById(anyLong());
        verify(userAssignmentDao, times(0)).update(any(UserAssignment.class));
    }

    @Test
    public void testReadById() {
        when(answerDao.readById(ID_1)).thenReturn(answer1);

        AnswerDTO actual = answerService.readById(ID_1);

        verify(answerDao).readById(anyLong());
        assertEquals(answerDTO1, actual);
    }

    @Test
    public void testUpdate() {
        when(answerDao.update(answer1Updated)).thenReturn(answer1Updated);
        when(answerDao.readById(ID_1)).thenReturn(answer1);
        when(userAssignmentDao.readById(USER_ASSIGNMENT_ID)).thenReturn(userAssignment);
        when(materialDao.getById(MATERIAL_ID)).thenReturn(material);
        when(userAssignmentDao.update(userAssignment)).thenReturn(userAssignment);

        AnswerDTO actual = answerService.update(answerDTO2, ID_1);

        verify(answerDao).update(any(Answer.class));
        verify(answerDao).readById(anyLong());
        verify(userAssignmentDao).readById(anyLong());
        verify(materialDao).getById(anyLong());
        verify(userAssignmentDao).update(any(UserAssignment.class));
        assertEquals(answerDTO1Updated, actual);
        assertEquals(LocalDateTime.now().withNano(0).withSecond(0), userAssignment.getSubmissionDate().withNano(0).withSecond(0));
    }

    @Test
    public void testUpdateThrowsSubmissionNotAllowedException() {
        when(answerDao.readById(ID_1)).thenReturn(answer1);
        when(userAssignmentDao.readById(USER_ASSIGNMENT_ID)).thenReturn(userAssignment);
        when(materialDao.getById(MATERIAL_ID)).thenReturn(material);
        material.setDueDate(DUE_DATE_2);

        SubmissionNotAllowedException exception = assertThrows(SubmissionNotAllowedException.class, () -> answerService.update(answerDTO2, ID_1));

        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
        verify(answerDao, times(0)).update(any(Answer.class));
        verify(answerDao).readById(anyLong());
        verify(userAssignmentDao).readById(anyLong());
        verify(materialDao).getById(anyLong());
        verify(userAssignmentDao, times(0)).update(any(UserAssignment.class));
    }

    @Test
    public void testDelete() {
        when(answerDao.readById(ID_1)).thenReturn(answer1);
        when(userAssignmentDao.readById(USER_ASSIGNMENT_ID)).thenReturn(userAssignment);
        when(materialDao.getById(MATERIAL_ID)).thenReturn(material);
        when(userAssignmentDao.update(userAssignment)).thenReturn(userAssignment);

        answerService.delete(ID_1);

        verify(answerDao).delete(anyLong());
        verify(answerDao).readById(anyLong());
        verify(userAssignmentDao).readById(anyLong());
        verify(materialDao).getById(anyLong());
        verify(userAssignmentDao).update(any(UserAssignment.class));
        assertEquals(LocalDateTime.now().withNano(0).withSecond(0), userAssignment.getSubmissionDate().withNano(0).withSecond(0));
    }

    @Test
    public void testDeleteThrowsSubmissionNotAllowedException() {
        when(answerDao.readById(ID_1)).thenReturn(answer1);
        when(userAssignmentDao.readById(USER_ASSIGNMENT_ID)).thenReturn(userAssignment);
        when(materialDao.getById(MATERIAL_ID)).thenReturn(material);
        material.setDueDate(DUE_DATE_2);

        SubmissionNotAllowedException exception = assertThrows(SubmissionNotAllowedException.class, () -> answerService.delete(ID_1));

        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
        verify(answerDao, times(0)).delete(anyLong());
        verify(answerDao).readById(anyLong());
        verify(userAssignmentDao).readById(anyLong());
        verify(materialDao).getById(anyLong());
        verify(userAssignmentDao, times(0)).update(any(UserAssignment.class));
    }

    @Test
    public void testGetByUserAssignment() {
        when(answerDao.getByUserAssignment(USER_ASSIGNMENT_ID)).thenReturn(Arrays.asList(answer1, answer2));

        List<AnswerDTO> actual = answerService.getByUserAssignment(USER_ASSIGNMENT_ID);

        verify(answerDao).getByUserAssignment(anyLong());
        assertEquals(Arrays.asList(answerDTO1, answerDTO2), actual);
    }
}
