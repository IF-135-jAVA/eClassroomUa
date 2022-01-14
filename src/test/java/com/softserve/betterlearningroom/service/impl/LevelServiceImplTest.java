package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.impl.LevelDaoImpl;
import com.softserve.betterlearningroom.dto.LevelDTO;
import com.softserve.betterlearningroom.entity.Level;
import com.softserve.betterlearningroom.mapper.LevelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LevelServiceImplTest {

    @InjectMocks
    private LevelServiceImpl levelService;

    @Mock
    private LevelDaoImpl levelDaoImpl;

    private Level expectedLevel;
    private LevelDTO expectedLevelDTO;

    @BeforeEach
    void setUp() {
        expectedLevelDTO = LevelDTO.builder()
                .id(1L)
                .title("Pythagorean theorem")
                .description("Write example")
                .criterionId(2L)
                .mark(5)
                .build();
        expectedLevel = Level.builder()
                .levelId(1L)
                .title("Pythagorean theorem")
                .description("Write example")
                .criterionId(2L)
                .mark(5)
                .build();
    }

    @Test
    void testGetById() {

        when(levelDaoImpl.findById(1L)).thenReturn(expectedLevel);

        LevelDTO byId = levelService.findById(1L);

        assertNotNull(byId);
        assertEquals(expectedLevel.getCriterionId(), byId.getCriterionId());
        assertEquals(expectedLevel.getDescription(), byId.getDescription());
        assertEquals(expectedLevel.getTitle(), byId.getTitle());
        assertEquals(expectedLevel.getMark(), byId.getMark());

        verify(levelDaoImpl).findById(1L);
    }

    @Test
    void testSaveLevelDTO() {

        when(levelDaoImpl.save(any(Level.class))).thenReturn(expectedLevel);

        levelService.save(expectedLevelDTO);
        assertNotNull(expectedLevel);
        assertEquals("Pythagorean theorem", expectedLevel.getTitle());
        assertEquals("Write example", expectedLevel.getDescription());
        assertEquals(2, expectedLevel.getCriterionId());
        assertEquals(5, expectedLevel.getMark());

        //verify(LevelMapper.toEntity(eq(expectedLevelDTO)));
    }

    @Test
    void testFindAll() {
        List<Level> listExpected = new ArrayList<Level>();
        listExpected.add(expectedLevel);
        List<LevelDTO> listToDtolistExpected = listExpected.stream().map(LevelMapper::toDTO).collect(Collectors.toList());

        when(levelDaoImpl.findAll()).thenReturn(listExpected);

        List<LevelDTO> listActual = levelService.findAll();

        assertEquals(listActual, listToDtolistExpected);
    }

    @Test
    void testUpdate() {
        when(levelDaoImpl.update(any(Level.class))).thenReturn(expectedLevel);

        levelService.update(expectedLevelDTO);

        assertNotNull(expectedLevel);
        assertEquals("Pythagorean theorem", expectedLevel.getTitle());
        assertEquals("Write example", expectedLevel.getDescription());
        assertEquals(2, expectedLevel.getCriterionId());
        assertEquals(5, expectedLevel.getMark());
    }


}