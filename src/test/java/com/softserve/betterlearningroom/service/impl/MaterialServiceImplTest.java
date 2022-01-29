package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.impl.MaterialDAOImpl;
import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.mapper.MaterialMapper;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MaterialServiceImplTest {
    @InjectMocks
    private MaterialServiceImpl materialService;

    @Mock
    private MaterialDAOImpl materialDAOImpl;
    @Mock
    private MaterialMapper materialMapper;

    private Material expectedMaterial;
    private MaterialDTO expectedMaterialDTO;

    @BeforeEach
    void setUp(){
        expectedMaterialDTO = MaterialDTO.builder()
                .id(1L)
                .title("title")
                .text("text")
                .classroomId(2L)
                .build();
        expectedMaterial = Material.builder()
                .id(1L)
                .title("title")
                .text("text")
                .classroomId(2L)
                .build();
    }

    @Test
    void testGetById() {
        when(materialDAOImpl.findById(1L)).thenReturn(expectedMaterial);
        when(materialMapper.materialToMaterialDTO(expectedMaterial)).thenReturn(expectedMaterialDTO);

        MaterialDTO byId = materialService.findMaterialById(1L);

        assertNotNull(byId);
        assertEquals(expectedMaterial.getId(), byId.getId());
        assertEquals(expectedMaterial.getTitle(), byId.getTitle());
        assertEquals(expectedMaterial.getClassroomId(), byId.getClassroomId());


        verify(materialDAOImpl).findById(1L);
    }

    @Test
    void testSaveLevelDTO() {

        when(materialDAOImpl.save((any(Material.class)), eq(1L))).thenReturn(expectedMaterial);
        when(materialMapper.materialToMaterialDTO(expectedMaterial)).thenReturn(expectedMaterialDTO);
        when(materialMapper.materialDTOToMaterial(expectedMaterialDTO)).thenReturn(expectedMaterial);

        materialService.save(expectedMaterialDTO, 1L);
        assertNotNull(expectedMaterial);
        assertEquals("title", expectedMaterial.getTitle());
        assertEquals("text", expectedMaterial.getText());
        assertEquals(2, expectedMaterial.getClassroomId());

    }
    @Test
    void testFindAll() {
        List<Material> listExpected = new ArrayList<Material>();
        listExpected.add(expectedMaterial);
        List<MaterialDTO> listToDtolistExpected = listExpected.stream().map(MaterialMapper::materialToMaterialDTO).collect(Collectors.toList());

        when(materialDAOImpl.findAllByClassroomId(1L)).thenReturn(listExpected);

        List<MaterialDTO> listActual = materialService.findAllMaterialsByClassroomId(1L);

        assertEquals(listActual, listToDtolistExpected);
    }

    @Test
    void testUpdate() {
        when(materialDAOImpl.update(any(Material.class))).thenReturn(expectedMaterial);

        materialService.update(expectedMaterialDTO);

        assertNotNull(expectedMaterial);
        assertEquals("title", expectedMaterial.getTitle());
        assertEquals("text", expectedMaterial.getText());
        assertEquals(2, expectedMaterial.getClassroomId());

    }

}