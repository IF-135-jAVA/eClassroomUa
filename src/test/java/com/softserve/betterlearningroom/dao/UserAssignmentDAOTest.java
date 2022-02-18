package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.impl.UserAssignmentDAOImpl;
import com.softserve.betterlearningroom.entity.UserAssignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = { TestDBConfiguration.class, UserAssignmentDAOImpl.class })
class UserAssignmentDAOTest {

    private static final Long ID_1 = 1L;
    private static final Long MATERIAL_ID_1 = 1L;
    private static final Long USER_ID_1 = 2L;
    private static final Long ASSIGNMENT_STATUS_ID_1 = 1L;
    private static final LocalDateTime SUBMISSION_DATE_1 = LocalDateTime.of(2022, 1, 26, 19, 25);
    private static final int GRADE_1 = 10;
    private static final String FEEDBACK_1 = "Good";
    private static final boolean ENABLED = true;
    private static final String MATERIAL_TITLE_1 = "The first assignment";
    private static final LocalDateTime DUE_DATE_1 = LocalDateTime.of(2022, 3, 26, 19, 25);
    private static final int MAX_SCORE_1 = 12;
    private static final String USER_FIRST_NAME_1 = "Yurii";
    private static final String USER_LAST_NAME_1 = "Kotsiuba";
    private static final String ASSIGNMENT_STATUS_TITLE_1 = "IN_PROGRESS";

    private static final Long ID_2 = 2L;
    private static final Long USER_ID_2 = 3L;
    private static final Long ASSIGNMENT_STATUS_ID_2 = 3L;
    private static final LocalDateTime SUBMISSION_DATE_2 = LocalDateTime.of(2022, 1, 28, 14, 15);
    private static final int GRADE_2 = 9;
    private static final String FEEDBACK_2 = "Almost good";
    private static final String MATERIAL_TITLE_2 = "The second assignment";
    private static final LocalDateTime DUE_DATE_2 = LocalDateTime.of(2022, 3, 15, 13, 0);
    private static final int MAX_SCORE_2 = 10;
    private static final String USER_FIRST_NAME_2 = "John";
    private static final String USER_LAST_NAME_2 = "Smith";
    private static final String ASSIGNMENT_STATUS_TITLE_2 = "DONE";

    private static final Long ID_3 = 3L;
    private static final Long MATERIAL_ID_2 = 2L;
    private static final Long MATERIAL_ID_3 = 3L;
    private static final String MATERIAL_TITLE_3 = "The third assignment";
    private static final LocalDateTime DUE_DATE_3 = LocalDateTime.of(2022, 4, 1, 12, 30);
    private static final int MAX_SCORE_3 = 100;

    private static final Long ID_4 = 4L;
    private static final Long ID_NOT_FOUND = 10L;

    private static final String EXCEPTION_MESSAGE_1 = "UserAssignment with id - " + ID_4 + ", not found.";
    private static final String EXCEPTION_MESSAGE_2 = "UserAssignment with id - " + ID_NOT_FOUND + ", not found.";

    private UserAssignment userAssignment1;
    private UserAssignment userAssignment2;
    private UserAssignment userAssignment3BeforeSaving;
    private UserAssignment userAssignment3;
    private UserAssignment userAssignment3Updated;

    @Autowired
    private UserAssignmentDAOImpl userAssignmentDao;

    @BeforeEach
    void setUp() {
        userAssignment1 = new UserAssignment(ID_1, MATERIAL_ID_1, MATERIAL_TITLE_1, DUE_DATE_1, MAX_SCORE_1, USER_ID_1, USER_FIRST_NAME_1, USER_LAST_NAME_1, ASSIGNMENT_STATUS_ID_1, ASSIGNMENT_STATUS_TITLE_1, SUBMISSION_DATE_1, GRADE_1, FEEDBACK_1, ENABLED);
        userAssignment2 = new UserAssignment(ID_2, MATERIAL_ID_1, MATERIAL_TITLE_1, DUE_DATE_1, MAX_SCORE_1, USER_ID_2, USER_FIRST_NAME_2, USER_LAST_NAME_2, ASSIGNMENT_STATUS_ID_2, ASSIGNMENT_STATUS_TITLE_2, SUBMISSION_DATE_2, GRADE_2, FEEDBACK_2, ENABLED);
        userAssignment3BeforeSaving = new UserAssignment(ID_3, MATERIAL_ID_3, null, null, 0, USER_ID_2, null, null, ASSIGNMENT_STATUS_ID_2, null, SUBMISSION_DATE_2, GRADE_2, FEEDBACK_2, ENABLED);
        userAssignment3 = new UserAssignment(ID_3, MATERIAL_ID_3, MATERIAL_TITLE_3, DUE_DATE_3, MAX_SCORE_3, USER_ID_2, USER_FIRST_NAME_2, USER_LAST_NAME_2, ASSIGNMENT_STATUS_ID_2, ASSIGNMENT_STATUS_TITLE_2, SUBMISSION_DATE_2, GRADE_2, FEEDBACK_2, ENABLED);
        userAssignment3Updated = new UserAssignment(ID_3, MATERIAL_ID_2, MATERIAL_TITLE_2, DUE_DATE_2, MAX_SCORE_2, USER_ID_1, USER_FIRST_NAME_1, USER_LAST_NAME_1, ASSIGNMENT_STATUS_ID_1, ASSIGNMENT_STATUS_TITLE_1, SUBMISSION_DATE_1, GRADE_1, FEEDBACK_1, ENABLED);
    }

    @Test
    void testSaveAndFindById() {
        UserAssignment savedUserAssignment = userAssignmentDao.save(userAssignment3BeforeSaving);
        UserAssignment foundUserAssignment = userAssignmentDao.findById(savedUserAssignment.getId());

        userAssignment3.setId(savedUserAssignment.getId());
        assertEquals(userAssignment3, savedUserAssignment);
        assertEquals(userAssignment3, foundUserAssignment);
    }

    @Test
    void testUpdateAndFindById() {
        userAssignment3.setMaterialId(MATERIAL_ID_2);
        userAssignment3.setUserId(USER_ID_1);
        userAssignment3.setAssignmentStatusId(ASSIGNMENT_STATUS_ID_1);
        userAssignment3.setSubmissionDate(SUBMISSION_DATE_1);
        userAssignment3.setGrade(GRADE_1);
        userAssignment3.setFeedback(FEEDBACK_1);
        UserAssignment updatedUserAssignment = userAssignmentDao.update(userAssignment3);
        UserAssignment foundUserAssignment = userAssignmentDao.findById(userAssignment3.getId());

        assertEquals(userAssignment3Updated, updatedUserAssignment);
        assertEquals(userAssignment3Updated, foundUserAssignment);
    }

    @Test
    void testDelete() {
        userAssignmentDao.delete(ID_4);

        DataRetrievalFailureException exception = assertThrows(DataRetrievalFailureException.class, () -> userAssignmentDao.findById(ID_4));
        assertEquals(EXCEPTION_MESSAGE_1, exception.getMessage());
    }

    @Test
    void testMethodsThrowDataRetrievalFailureException() {
        userAssignment3.setId(ID_NOT_FOUND);

        DataRetrievalFailureException exception1 = assertThrows(DataRetrievalFailureException.class, () -> userAssignmentDao.findById(ID_NOT_FOUND));
        DataRetrievalFailureException exception2 = assertThrows(DataRetrievalFailureException.class, () -> userAssignmentDao.update(userAssignment3));
        DataRetrievalFailureException exception3 = assertThrows(DataRetrievalFailureException.class, () -> userAssignmentDao.delete(ID_NOT_FOUND));

        assertEquals(EXCEPTION_MESSAGE_2, exception1.getMessage());
        assertEquals(EXCEPTION_MESSAGE_2, exception2.getMessage());
        assertEquals(EXCEPTION_MESSAGE_2, exception3.getMessage());
    }

    @Test
    void testFindByAssignmentId() {
        List<UserAssignment> actual = userAssignmentDao.findByAssignmentId(MATERIAL_ID_1);

        assertEquals(Arrays.asList(userAssignment1, userAssignment2), actual);
    }
}
