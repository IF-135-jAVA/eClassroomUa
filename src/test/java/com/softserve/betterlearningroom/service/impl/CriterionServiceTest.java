package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.impl.CriterionDaoImpl;
import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.entity.Criterion;
import com.softserve.betterlearningroom.mapper.CriterionMapper;
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
class CriterionServiceTest {

    @InjectMocks
    private CriterionService criterionService;

    @Mock
    private CriterionDaoImpl criterionDAOImpl;

    private CriterionDTO expectedCriterionDTO;
    private Criterion expectedCriterion;


    @BeforeEach
    void setUp() {
        expectedCriterionDTO = CriterionDTO.builder()
                .id(1)
                .materialIdDTO(2)
                .title("Using formula")
                .description("Using wright formula")
                .build();
        expectedCriterion = Criterion.builder()
                .criterionId(1)
                .materialId(2)
                .title("Using formula")
                .description("Using wright formula")
                .build();
    }

    @Test
    void testGetById(){

        when(criterionDAOImpl.findById(1L)).thenReturn(expectedCriterion);

        CriterionDTO byId = criterionService.findById(1L);

        assertNotNull(byId);
        assertEquals(expectedCriterion.getMaterialId(), byId.getMaterialIdDTO());
        assertEquals(expectedCriterion.getDescription(), byId.getDescription());
        assertEquals(expectedCriterion.getTitle(), byId.getTitle());
        verify(criterionDAOImpl).findById(1L);
    }
    @Test
    void testSaveCriterionDTO() {

        when(criterionDAOImpl.save(any(Criterion.class))).thenReturn(expectedCriterion);

        criterionService.save(expectedCriterionDTO);
        assertNotNull(expectedCriterion);
        assertEquals("Using formula", expectedCriterion.getTitle());
        assertEquals("Using wright formula", expectedCriterion.getDescription());
        assertEquals(2, expectedCriterion.getMaterialId());

        //verify(CriterionMapper.toEntity(eq(expectedCriterionDTO)));
    }

    @Test
    void testFindAll() {
        List<Criterion> listExpected = new ArrayList<Criterion>();
        listExpected.add(expectedCriterion);
        List<CriterionDTO> listToDtolistExpected = listExpected.stream().map(CriterionMapper::toDTO).collect(Collectors.toList());

        when(criterionDAOImpl.findAll()).thenReturn(listExpected);

        List<CriterionDTO> listActual = criterionService.findAll();

        assertEquals(listActual, listToDtolistExpected);
    }

    @Test
    void testUpdate() {
        when(criterionDAOImpl.update(any(Criterion.class))).thenReturn(expectedCriterion);

        criterionService.update(expectedCriterionDTO);

        assertNotNull(expectedCriterion);
        assertEquals("Using formula", expectedCriterion.getTitle());
        assertEquals("Using wright formula", expectedCriterion.getDescription());
        assertEquals(2, expectedCriterion.getMaterialId());
    }


}