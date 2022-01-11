package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.entity.Criterion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {TestDBConfiguration.class, CriterionDAO.class})
class CriterionDAOTest {

    public static final int CRITERION_ID = 1;
    public static final String TITLE = "Using wright formula";
    public static final String DESCRIPTION = "Using wright formula";
    public static final int MATERIAL_ID = 1;

    @Autowired
    CriterionDAO criterionDAO;

    @Test
    public void testSaveAndGet(){
        Criterion expectedCriterion = Criterion.builder()
                .criterionId(CRITERION_ID)
                .materialId(MATERIAL_ID)
                .title(TITLE)
                .description(DESCRIPTION)
                .build();
        criterionDAO.save(expectedCriterion);

        Criterion byId = criterionDAO.findById(CRITERION_ID);
        assertNotNull(byId);
        assertEquals(TITLE, byId.getTitle());
        assertEquals(DESCRIPTION,byId.getDescription());
        assertEquals(MATERIAL_ID, byId.getMaterialId());
    }



}