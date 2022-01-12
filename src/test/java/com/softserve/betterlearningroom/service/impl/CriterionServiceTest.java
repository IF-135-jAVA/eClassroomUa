package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.impl.CriterionDAO;
import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.entity.Criterion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriterionServiceTest {
    //@Spy
    @InjectMocks
    private CriterionService criterionService;

    @Mock
    private CriterionDAO criterionDAO;

//    private CriterionDTO expectedCriterionDTO;
//    private Criterion expectedCriterion;


//    @BeforeEach
//    void setUp() {
//        expectedCriterionDTO = CriterionDTO.builder()
//                .id(12)
//                .materialIdDTO(4)
//                .title("title")
//                .description("desc")
//                .build();
//        expectedCriterion = Criterion.builder()
//                .criterionId(12)
//                .materialId(4)
//                .title("title")
//                .description("desc")
//                .build();
//    }
    private Criterion expectedCriterion(){
        return  Criterion.builder()
                .criterionId(1)
                .materialId(2)
                .title("Using wright formula")
                .description("Using wright formula")
                .build();
    }

    @Test
    public void testGetById(){
        Criterion criterion = expectedCriterion();
        when(criterionDAO.findById(1)).thenReturn(criterion);

        CriterionDTO byId = criterionService.findById(1);

        assertNotNull(byId);
        assertEquals(criterion.getMaterialId(), byId.getMaterialIdDTO());
        assertEquals(criterion.getDescription(), byId.getDescription());
        assertEquals(criterion.getTitle(), byId.getTitle());
    }
//    @Test
//    public void shouldSaveCriterionDTO() {
//        when(criterionDAO).save(any(Criterion.class));
//
//        criterionService.save(expectedCriterionDTO);
//
//        verify(CriterionMapper.toEntity(eq(expectedCriterionDTO)));
//    }

//    @Test
//    void shouldFindAll() {
//        List<Criterion> listExpected = new ArrayList<Criterion>();
//        listExpected.add(expectedCriterion);
//        List<CriterionDTO> listToDtolistExpected = listExpected.stream().map(CriterionMapper::toDTO).collect(Collectors.toList());
//        when(criterionDAO.findAll()).thenReturn(listExpected);
//
//        List<CriterionDTO> listActual = criterionService.findAll();
//
//        Assertions.assertEquals(listActual, listToDtolistExpected);
//    }
//
//    @Test
//    void shouldFindById() {
//       when(criterionDAO.findById(12)).thenReturn(expectedCriterion);
//
//        CriterionDTO actualCriterionDTO = criterionService.findById(12);
//
//        Assertions.assertEquals(actualCriterionDTO, expectedCriterionDTO);
//    }
//
//    @Test
//    void shouldFindByIdThrowError() {
//        when(criterionDAO.findById(12)).thenReturn(null);
//
//        assertThrows(RuntimeException.class, () -> criterionService.findById(12));
//    }
}