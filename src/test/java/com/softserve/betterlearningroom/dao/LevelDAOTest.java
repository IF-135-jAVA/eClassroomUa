package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.impl.LevelDAOImpl;
import com.softserve.betterlearningroom.entity.Level;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {TestDBConfiguration.class, LevelDAOImpl.class})
class LevelDAOTest {
    private static final Long LEVEL_ID = 3L;
    private static final String TITLE = "Pythagorean theorem";
    private static final String DESCRIPTION = "Write example";
    private static final long CRITERION_ID = 3;
    private static final Integer MARK = 4;

    @Autowired
    private LevelDAOImpl levelDao;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testSaveAndGet() {
        Level levelForSave = Level.builder()
                .levelId(LEVEL_ID)
                .title(TITLE)
                .description(DESCRIPTION)
                .criterionId(CRITERION_ID)
                .mark(MARK)
                .build();

        levelDao.save(levelForSave);

        Level byId = levelDao.findById(CRITERION_ID);
        assertNotNull(byId);
        assertEquals(TITLE, byId.getTitle());
        assertEquals(DESCRIPTION, byId.getDescription());
        assertEquals(CRITERION_ID, byId.getCriterionId());
        assertEquals(MARK, byId.getMark());
    }

    @Test
    void testfindAll() {
        Level levelForSave = Level.builder()
                .levelId(4L)
                .criterionId(CRITERION_ID)
                .title(TITLE)
                .description(DESCRIPTION)
                .mark(MARK)
                .build();

        levelDao.save(levelForSave);

        List<Level> expectedlevels = levelDao.findAll();

        assertEquals(3, expectedlevels.size());
        Level expectedLevel = expectedlevels.stream().filter(level -> level.getLevelId() == 4L).findFirst().orElse(null);
        assertNotNull(expectedLevel);
        assertEquals(TITLE, expectedLevel.getTitle());
        assertEquals(DESCRIPTION, expectedLevel.getDescription());
        assertEquals(CRITERION_ID, expectedLevel.getCriterionId());
        assertEquals(MARK, expectedLevel.getMark());
    }

    @Test
    void testUpdate() {
        Level levelForSave = Level.builder()
                .levelId(4L)
                .criterionId(4L)
                .title("test title")
                .description("test description")
                .mark(MARK)
                .build();

        levelDao.update(levelForSave);

        Level updatedLevel = levelDao.findById(4L);
        assertNotNull(updatedLevel);
        assertEquals("test title", updatedLevel.getTitle());
        assertEquals("test description", updatedLevel.getDescription());
        assertEquals(4, updatedLevel.getCriterionId());
    }
}