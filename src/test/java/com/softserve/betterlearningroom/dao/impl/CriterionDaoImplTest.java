package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.CriterionDao;
import com.softserve.betterlearningroom.entity.Criterion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {TestDBConfiguration.class, CriterionDaoImpl.class})
class CriterionDaoImplTest {

    private static final long CRITERION_ID = 3;
    private static final String TITLE = "Using wright formula";
    private static final String DESCRIPTION = "Using wright formula";
    private static final int MATERIALID = 1;

    @Autowired
    private CriterionDao criterionDao;


    @Test
     void testSaveAndGet(){
        Criterion expectedCriterion = Criterion.builder()
                .criterionId((int)CRITERION_ID)
                .materialId(MATERIALID)
                .title(TITLE)
                .description(DESCRIPTION)
                .build();
        criterionDao.save(expectedCriterion);

        Criterion byId = criterionDao.findById(CRITERION_ID);
        assertNotNull(byId);
        assertEquals(TITLE, byId.getTitle());
        assertEquals(DESCRIPTION,byId.getDescription());
        assertEquals(MATERIALID, byId.getMaterialId());
    }
    @Test
    void testfindAll(){
        Criterion criterionForSave = Criterion.builder()
                .criterionId((int)CRITERION_ID)
                .materialId(MATERIALID)
                .title(TITLE)
                .description(DESCRIPTION)
                .build();

        criterionDao.save(criterionForSave);

        List<Criterion> expectedCriterions = criterionDao.findAll();

        assertEquals(3, expectedCriterions.size());
        Criterion expectedCriterion = expectedCriterions.stream().filter(criterion -> criterion.getCriterionId()==3L).findFirst().orElse(null);
        assertNotNull(expectedCriterion);
        assertEquals(TITLE, expectedCriterion.getTitle());
        assertEquals(DESCRIPTION,expectedCriterion.getDescription());
        assertEquals(MATERIALID, expectedCriterion.getMaterialId());
    }
    @Test
    void testUpdate(){
        Criterion criterionForSave = Criterion.builder()
                .criterionId(1)
                .materialId(5)
                .title("test title")
                .description("test description")
                .build();
        criterionDao.update(criterionForSave);

        Criterion byId = criterionDao.findById(1L);
        assertNotNull(byId);
        assertEquals("test title", byId.getTitle());
        assertEquals("test description", byId.getDescription());
        assertEquals(5, byId.getMaterialId());
    }





}