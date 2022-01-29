package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.MaterialDAO;
import com.softserve.betterlearningroom.entity.Material;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestDBConfiguration.class, MaterialDAOImpl.class})
class MaterialDAOImplTest {

    @Autowired
    private MaterialDAO materialDAO;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testSaveAndGet() {
        Material materialForSave = Material.builder()
                .id(4L)
                .title("title")
                .text("text")
                .classroomId(2L)
                .topicId(2L)
                .build();

        materialDAO.save(materialForSave, 2L);

        Material byId = materialDAO.findById(4L);

        assertNotNull(byId);
        assertEquals("title", byId.getTitle());
        assertEquals("text", byId.getText());
        assertEquals(2L, byId.getTopicId());

    }
    @Test
    void testFindAll() {
        Material materialForSave = Material.builder()
                .id(4L)
                .title("title")
                .text("text")
                .classroomId(2L)
                .topicId(2L)
                .build();
        materialDAO.save(materialForSave, 2L);


        List<Material> expectedMaterials = materialDAO.findAllByClassroomId(2L);
        Material expectedMaterial = expectedMaterials.stream().filter(material -> material.getId()==4L).findFirst().orElse(null);

        assertNotNull(expectedMaterial);
        assertEquals("title", expectedMaterial.getTitle());
        assertEquals("text", expectedMaterial.getText());
        assertEquals(2L, expectedMaterial.getTopicId());
    }



}