package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.impl.CriterionDAOImpl;
import com.softserve.betterlearningroom.entity.Criterion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {TestDBConfiguration.class, CriterionDAOImpl.class})
class CriterionDAOTest {
    private static final long CRITERION_ID = 3;
    private static final String TITLE = "Using wright formula";
    private static final String DESCRIPTION = "Using wright formula";
    private static final Long MATERIAL_ID = 1L;

    @Autowired
    private CriterionDAO criterionDao;

    @Test
    void testSaveAndGet() {
        Criterion criterionForSave = Criterion.builder()
                .criterionId(CRITERION_ID)
                .materialId(MATERIAL_ID)
                .title(TITLE)
                .description(DESCRIPTION)
                .build();
        criterionDao.save(criterionForSave);

        Criterion byId = criterionDao.findById(CRITERION_ID);
        assertNotNull(byId);
        assertEquals(TITLE, byId.getTitle());
        assertEquals(DESCRIPTION, byId.getDescription());
        assertEquals(MATERIAL_ID, byId.getMaterialId());
    }

    @Test
    void testfindAll() {
        Criterion criterionForSave = Criterion.builder()
                .criterionId(4L)
                .materialId(MATERIAL_ID)
                .title(TITLE)
                .description(DESCRIPTION)
                .build();

        criterionDao.save(criterionForSave);

        List<Criterion> expectedCriterions = criterionDao.findAll();

        assertEquals(4, expectedCriterions.size());
        Criterion expectedCriterion = expectedCriterions.stream().filter(criterion -> criterion.getCriterionId() == 3L).findFirst().orElse(null);
        assertNotNull(expectedCriterion);
        assertEquals(TITLE, expectedCriterion.getTitle());
        assertEquals(DESCRIPTION, expectedCriterion.getDescription());
        assertEquals(MATERIAL_ID, expectedCriterion.getMaterialId());
    }

    @Test
    void testUpdate() {
        Criterion criterionForSave = Criterion.builder()
                .criterionId(1L)
                .materialId(5L)
                .title("test title")
                .description("test description")
                .build();
        criterionDao.update(criterionForSave);

        Criterion updatedCriterion = criterionDao.findById(1L);
        assertNotNull(updatedCriterion);
        assertEquals("test title", updatedCriterion.getTitle());
        assertEquals("test description", updatedCriterion.getDescription());
        assertEquals(5, updatedCriterion.getMaterialId());
    }
}