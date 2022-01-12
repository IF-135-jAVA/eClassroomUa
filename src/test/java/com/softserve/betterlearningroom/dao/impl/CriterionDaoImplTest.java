package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.entity.Criterion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {TestDBConfiguration.class, CriterionDaoImpl.class})
class CriterionDaoImplTest {

    private static final long CRITERION_ID = 3;
    private static final String TITLE = "Using wright formula";
    private static final String DESCRIPTION = "Using wright formula";
    private static final int MATERIALID = 1;

    @Autowired
    private CriterionDaoImpl criterionDAOImpl;

    @Test
     void testSaveAndGet(){
        Criterion expectedCriterion = Criterion.builder()
                .criterionId((int)CRITERION_ID)
                .materialId(MATERIALID)
                .title(TITLE)
                .description(DESCRIPTION)
                .build();
        criterionDAOImpl.save(expectedCriterion);

        Criterion byId = criterionDAOImpl.findById(CRITERION_ID);
        assertNotNull(byId);
        assertEquals(TITLE, byId.getTitle());
        assertEquals(DESCRIPTION,byId.getDescription());
        assertEquals(MATERIALID, byId.getMaterialId());
    }
    @Test
    void testfindAll(){
        Criterion expectedCriterion = Criterion.builder()
                .criterionId((int)CRITERION_ID)
                .materialId(MATERIALID)
                .title(TITLE)
                .description(DESCRIPTION)
                .build();
        criterionDAOImpl.save(expectedCriterion);

        System.out.println("KKK"+criterionDAOImpl.findAll().size());
    }





}