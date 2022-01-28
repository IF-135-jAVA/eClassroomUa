package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.impl.AnswerDaoImpl;
import com.softserve.betterlearningroom.entity.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = { TestDBConfiguration.class, AnswerDaoImpl.class })
public class AnswerDAOTest {

    private static final long ID_1 = 1;
    private static final long USER_ASSIGNMENT_ID_1 = 1;
    private static final String TEXT_1 = "Monday";
    private static final boolean ENABLED = true;

    private static final long ID_2 = 2;
    private static final String TEXT_2 = "Tuesday";

    private static final long ID_3 = 3;
    private static final long USER_ASSIGNMENT_ID_2 = 2;
    private static final long USER_ASSIGNMENT_ID_3 = 3;

    private static final long ID_4 = 4;
    private static final long ID_NOT_FOUND = 10;

    private static final String EXCEPTION_MESSAGE_1 = "Answer with id - " + ID_4 + ", not found.";
    private static final String EXCEPTION_MESSAGE_2 = "Answer with id - " + ID_NOT_FOUND + ", not found.";

    private Answer answer1;
    private Answer answer2;
    private Answer answer3;

    @Autowired
    private AnswerDaoImpl answerDao;

    @BeforeEach
    public void setUp() {
        answer1 = new Answer(ID_1, USER_ASSIGNMENT_ID_1, TEXT_1, ENABLED);
        answer2 = new Answer(ID_2, USER_ASSIGNMENT_ID_1, TEXT_2, ENABLED);
        answer3 = new Answer(ID_3, USER_ASSIGNMENT_ID_3, TEXT_2, ENABLED);
    }

    @Test
    public void testSaveAndFindById() {
        Answer savedAnswer = answerDao.create(answer3);
        Answer foundAnswer = answerDao.readById(savedAnswer.getId());

        answer3.setId(savedAnswer.getId());
        assertEquals(answer3, savedAnswer);
        assertEquals(answer3, foundAnswer);
    }

    @Test
    public void testUpdateAndFindById() {
        answer3.setUserAssignmentId(USER_ASSIGNMENT_ID_2);
        answer3.setText(TEXT_1);
        Answer updatedAnswer = answerDao.update(answer3);
        Answer foundAnswer = answerDao.readById(answer3.getId());

        assertEquals(answer3, updatedAnswer);
        assertEquals(answer3, foundAnswer);
    }

    @Test
    public void testDelete() {
        answerDao.delete(ID_4);

        DataRetrievalFailureException exception = assertThrows(DataRetrievalFailureException.class, () -> answerDao.readById(ID_4));
        assertEquals(EXCEPTION_MESSAGE_1, exception.getMessage());
    }

    @Test
    public void testMethodsThrowDataRetrievalFailureException() {
        answer3.setId(ID_NOT_FOUND);

        DataRetrievalFailureException exception1 = assertThrows(DataRetrievalFailureException.class, () -> answerDao.readById(ID_NOT_FOUND));
        DataRetrievalFailureException exception2 = assertThrows(DataRetrievalFailureException.class, () -> answerDao.update(answer3));
        DataRetrievalFailureException exception3 = assertThrows(DataRetrievalFailureException.class, () -> answerDao.delete(ID_NOT_FOUND));

        assertEquals(EXCEPTION_MESSAGE_2, exception1.getMessage());
        assertEquals(EXCEPTION_MESSAGE_2, exception2.getMessage());
        assertEquals(EXCEPTION_MESSAGE_2, exception3.getMessage());
    }

    @Test
    public void testFindByUserAssignmentId() {
        List<Answer> actual = answerDao.getByUserAssignment(USER_ASSIGNMENT_ID_1);

        assertEquals(Arrays.asList(answer1, answer2), actual);
    }
}
