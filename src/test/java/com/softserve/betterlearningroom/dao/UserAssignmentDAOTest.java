package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.impl.UserAssignmentDaoImpl;
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

@SpringBootTest(classes = { TestDBConfiguration.class, UserAssignmentDaoImpl.class })
public class UserAssignmentDAOTest {

    private static final long ID_1 = 1;
    private static final long MATERIAL_ID_1 = 1;
    private static final long USER_ID_1 = 2;
    private static final long ASSIGNMENT_STATUS_ID_1 = 1;
    private static final LocalDateTime SUBMISSION_DATE_1 = LocalDateTime.of(2022, 1, 26, 19, 25);
    private static final int GRADE_1 = 10;
    private static final String FEEDBACK_1 = "Good";
    private static final boolean ENABLED = true;

    private static final long ID_2 = 2;
    private static final long USER_ID_2 = 3;
    private static final long ASSIGNMENT_STATUS_ID_2 = 3;
    private static final LocalDateTime SUBMISSION_DATE_2 = LocalDateTime.of(2022, 1, 28, 14, 15);
    private static final int GRADE_2 = 9;
    private static final String FEEDBACK_2 = "Almost good";

    private static final long ID_3 = 3;
    private static final long MATERIAL_ID_2 = 2;
    private static final long MATERIAL_ID_3 = 3;

    private static final long ID_4 = 4;
    private static final long ID_NOT_FOUND = 10;

    private static final String EXCEPTION_MESSAGE_1 = "UserAssignment with id - " + ID_4 + ", not found.";
    private static final String EXCEPTION_MESSAGE_2 = "UserAssignment with id - " + ID_NOT_FOUND + ", not found.";

    private UserAssignment userAssignment1;
    private UserAssignment userAssignment2;
    private UserAssignment userAssignment3;

    @Autowired
    private UserAssignmentDaoImpl userAssignmentDao;

    @BeforeEach
    public void setUp() {
        userAssignment1 = new UserAssignment(ID_1, MATERIAL_ID_1, USER_ID_1, ASSIGNMENT_STATUS_ID_1, SUBMISSION_DATE_1, GRADE_1, FEEDBACK_1, ENABLED);
        userAssignment2 = new UserAssignment(ID_2, MATERIAL_ID_1, USER_ID_2, ASSIGNMENT_STATUS_ID_2, SUBMISSION_DATE_2, GRADE_2, FEEDBACK_2, ENABLED);
        userAssignment3 = new UserAssignment(ID_3, MATERIAL_ID_3, USER_ID_2, ASSIGNMENT_STATUS_ID_2, SUBMISSION_DATE_2, GRADE_2, FEEDBACK_2, ENABLED);
    }

    @Test
    public void testSaveAndFindById() {
        UserAssignment savedUserAssignment = userAssignmentDao.create(userAssignment3);
        UserAssignment foundUserAssignment = userAssignmentDao.readById(savedUserAssignment.getId());

        userAssignment3.setId(savedUserAssignment.getId());
        assertEquals(userAssignment3, savedUserAssignment);
        assertEquals(userAssignment3, foundUserAssignment);
    }

    @Test
    public void testUpdateAndFindById() {
        userAssignment3.setMaterialId(MATERIAL_ID_2);
        userAssignment3.setUserId(USER_ID_1);
        userAssignment3.setAssignmentStatusId(ASSIGNMENT_STATUS_ID_1);
        userAssignment3.setSubmissionDate(SUBMISSION_DATE_1);
        userAssignment3.setGrade(GRADE_1);
        userAssignment3.setFeedback(FEEDBACK_1);
        UserAssignment updatedUserAssignment = userAssignmentDao.update(userAssignment3);
        UserAssignment foundUserAssignment = userAssignmentDao.readById(userAssignment3.getId());

        assertEquals(userAssignment3, updatedUserAssignment);
        assertEquals(userAssignment3, foundUserAssignment);
    }

    @Test
    public void testDelete() {
        userAssignmentDao.delete(ID_4);

        DataRetrievalFailureException exception = assertThrows(DataRetrievalFailureException.class, () -> userAssignmentDao.readById(ID_4));
        assertEquals(EXCEPTION_MESSAGE_1, exception.getMessage());
    }

    @Test
    public void testMethodsThrowDataRetrievalFailureException() {
        userAssignment3.setId(ID_NOT_FOUND);

        DataRetrievalFailureException exception1 = assertThrows(DataRetrievalFailureException.class, () -> userAssignmentDao.readById(ID_NOT_FOUND));
        DataRetrievalFailureException exception2 = assertThrows(DataRetrievalFailureException.class, () -> userAssignmentDao.update(userAssignment3));
        DataRetrievalFailureException exception3 = assertThrows(DataRetrievalFailureException.class, () -> userAssignmentDao.delete(ID_NOT_FOUND));

        assertEquals(EXCEPTION_MESSAGE_2, exception1.getMessage());
        assertEquals(EXCEPTION_MESSAGE_2, exception2.getMessage());
        assertEquals(EXCEPTION_MESSAGE_2, exception3.getMessage());
    }

    @Test
    public void testFindByAssignmentId() {
        List<UserAssignment> actual = userAssignmentDao.getByAssignment(MATERIAL_ID_1);

        assertEquals(Arrays.asList(userAssignment1, userAssignment2), actual);
    }
}
