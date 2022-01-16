package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.AnswerDao;
import com.softserve.betterlearningroom.dao.MaterialDao;
import com.softserve.betterlearningroom.dao.UserAssignmentDao;
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
public class UserAssignmentServiceTest {

    private static final long ID_1 = 1;
    private static final long MATERIAL_ID = 1;
    private static final long USER_ID_1 = 2;
    private static final long ASSIGNMENT_STATUS_ID_1 = 1;
    private static final LocalDateTime SUBMISSION_DATE_1 = null;
    private static final int GRADE_1 = 0;
    private static final String FEEDBACK_1 = null;
    private static final boolean ENABLED = true;

    private static final long ID_2 = 2;
    private static final long USER_ID_2 = 3;
    private static final long ASSIGNMENT_STATUS_ID_2 = 3;
    private static final LocalDateTime SUBMISSION_DATE_2 = LocalDateTime.now().minusDays(2);
    private static final int GRADE_2 = 9;
    private static final String FEEDBACK_2 = "Almost good";

    private static final LocalDateTime DUE_DATE_1 = LocalDateTime.now().plusDays(1);

    private static final LocalDateTime DUE_DATE_2 = LocalDateTime.now().minusDays(3);

    private static final long ANSWER_ID_1 = 1;

    private static final long ANSWER_ID_2 = 2;

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
    private UserAssignmentDao userAssignmentDao;

    @Mock
    private AnswerDao answerDao;

    @Mock
    private MaterialDao materialDao;

    @InjectMocks
    private UserAssignmentServiceImpl userAssignmentService;

    @BeforeEach
    public void setUp() {
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
    public void testCreate() {
        when(userAssignmentDao.create(userAssignment1)).thenReturn(userAssignment1);
        when(materialDao.readById(MATERIAL_ID)).thenReturn(material);

        UserAssignmentDTO actual = userAssignmentService.create(userAssignmentDTO1);

        verify(userAssignmentDao).create(any(UserAssignment.class));
        verify(materialDao).readById(anyLong());
        assertEquals(userAssignmentDTO1, actual);
    }

    @Test
    public void testCreateThrowsSubmissionNotAllowedException() {
        when(materialDao.readById(MATERIAL_ID)).thenReturn(material);
        material.setDueDate(DUE_DATE_2);

        SubmissionNotAllowedException exception = assertThrows(SubmissionNotAllowedException.class, () -> userAssignmentService.create(userAssignmentDTO1));

        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
        verifyNoInteractions(userAssignmentDao);
        verify(materialDao).readById(anyLong());
    }

    @Test
    public void testReadById() {
        when(userAssignmentDao.readById(ID_2)).thenReturn(userAssignment2);

        UserAssignmentDTO actual = userAssignmentService.readById(ID_2);

        verify(userAssignmentDao).readById(anyLong());
        assertEquals(userAssignmentDTO2, actual);
    }

    @Test
    public void testUpdate() {
        when(userAssignmentDao.update(userAssignment1Updated)).thenReturn(userAssignment1Updated);
        when(userAssignmentDao.readById(ID_1)).thenReturn(userAssignment1);

        UserAssignmentDTO actual = userAssignmentService.update(userAssignmentDTO2, ID_1);

        verify(userAssignmentDao).update(any(UserAssignment.class));
        verify(userAssignmentDao).readById(anyLong());
        assertEquals(userAssignmentDTO1Updated, actual);
    }

    @Test
    public void testDelete() {
        List<Answer> answers = Arrays.asList(answer1, answer2);
        when(answerDao.getByUserAssignment(ID_2)).thenReturn(answers);

        userAssignmentService.delete(ID_2);

        verify(userAssignmentDao).delete(anyLong());
        verify(answerDao).getByUserAssignment(anyLong());
        verify(answerDao, times(answers.size())).delete(anyLong());
    }

    @Test
    public void testGetByAssignment() {
        when(userAssignmentDao.getByAssignment(MATERIAL_ID)).thenReturn(Arrays.asList(userAssignment1, userAssignment2));

        List<UserAssignmentDTO> actual = userAssignmentService.getByAssignment(MATERIAL_ID);

        verify(userAssignmentDao).getByAssignment(anyLong());
        assertEquals(Arrays.asList(userAssignmentDTO1, userAssignmentDTO2), actual);
    }
}
