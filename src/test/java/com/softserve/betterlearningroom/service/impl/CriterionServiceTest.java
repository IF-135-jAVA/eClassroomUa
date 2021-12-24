package com.softserve.betterlearningroom.service.impl;
import com.softserve.betterlearningroom.dao.impl.CriterionDAO;
import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.entity.Criterion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriterionServiceTest {

    @InjectMocks
    @Spy
    private CriterionService criterionService;

    @Mock
    private CriterionDAO criterionDAO;

    private CriterionDTO expectedCriterionDTO;
    private Criterion expectedCriterion;

    @BeforeEach
    void setUp() {
        expectedCriterionDTO = CriterionDTO.builder()
                .id(12)
                .materialIdDTO(4)
                .title("title")
                .description("desc")
                .build();
        expectedCriterion = Criterion.builder()
                .criterion_id(12)
                .materialid(4)
                .title("title")
                .description("desc")
                .build();
    }

    @Test
    void shouldSaveCriterionDTO() {
        doNothing().when(criterionDAO).save(any(Criterion.class));

        criterionService.save(expectedCriterionDTO);

        verify(criterionService).toEntity(eq(expectedCriterionDTO));
    }

    @Test
    void  shouldFindAll(){
        List<Criterion> listExpected = new ArrayList<Criterion>();
        listExpected.add(expectedCriterion);
        List<CriterionDTO> listToDtolistExpected = listExpected.stream().map(criterionService::toDTO).collect(Collectors.toList());
        when(criterionDAO.findAll()).thenReturn(listExpected);

        List<CriterionDTO> listActual = criterionService.findAll();

        Assertions.assertEquals(listActual, listToDtolistExpected );
    }

    @Test
    void shouldFindById() {
        when(criterionDAO.findById(12)).thenReturn(Optional.of(expectedCriterion));

        CriterionDTO actualCriterionDTO = criterionService.findById(12);

        Assertions.assertEquals(actualCriterionDTO, expectedCriterionDTO);
    }

    @Test
    void shouldFindByIdThrowError() {
        when(criterionDAO.findById(12)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> criterionService.findById(12));
    }
}