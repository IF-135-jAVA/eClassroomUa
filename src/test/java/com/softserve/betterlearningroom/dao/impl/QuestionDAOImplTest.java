package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.QuestionDAO;
import com.softserve.betterlearningroom.entity.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {TestDBConfiguration.class, QuestionDAOImpl.class})
class QuestionDAOImplTest {

    @Autowired
    QuestionDAO questionDAO;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testSaveAndGet() {
        Question questionForsave = Question.builder()
                .id(2L)
                .question("When")
                .build();
        questionDAO.save(questionForsave, 2L);
        System.out.println(questionDAO.findAllByMaterialId(2L).get(0));
        Question byId = questionDAO.findAllByMaterialId(2L).get(0);
        assertNotNull(byId);
        assertEquals("When", byId.getQuestion());
        assertEquals(2L, byId.getId());
    }

    @Test
    void testUpdate() {
        Question questionForsave = Question.builder()
                .id(2L)
                .question("When")
                .build();

        questionDAO.save(questionForsave, 2L);
        questionDAO.update(questionForsave);

        assertNotNull(questionForsave);
        assertEquals("When",questionForsave.getQuestion() );
        assertEquals(2L,questionForsave.getId() );
    }

    @Test
    void testDelete() {
        Question questionForsave = Question.builder()
                .id(2L)
                .question("When")
                .build();

        questionDAO.save(questionForsave, 4L);
        questionDAO.delete(2L);

        assertEquals(null, questionDAO.findAllByMaterialId(4L) );

    }

}