package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.LinkDAO;
import com.softserve.betterlearningroom.entity.Link;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {TestDBConfiguration.class, LinkDAOImpl.class})
class LinkDAOImplTest {

    private static final Long ID =3L;
    private static final Long MATERIAL_ID =2L;
    private static final String URL = "Belero.com.ua";
    private static final String TEXT = "text";

    @Autowired
    private LinkDAO linkDAO;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testSaveAndGet() {
        Link linkForSave = Link.builder()
                //.id(ID)
                .url(URL)
                .text(TEXT)
                .build();
        linkDAO.save(linkForSave, MATERIAL_ID);

        Link byId = linkDAO.findAllByMaterialId(2L).get(0);
        assertNotNull(byId);
        assertEquals(URL, byId.getUrl());
        assertEquals(TEXT, byId.getText());
    }

    @Test
    void testUpdate() {
        Link linkForSave = Link.builder()
                .id(ID)
                .url("update url")
                .text("update text")
                .build();


        linkDAO.update(linkForSave);

        assertNotNull(linkForSave);
        assertEquals("update url",linkForSave.getUrl() );
        assertEquals("update text",linkForSave.getText() );
    }

    @Test
    void deleteTest(){
        Link linkForSave = Link.builder()
                .id(ID)
                .url("update url")
                .text("update text")
                .build();

        linkDAO.save(linkForSave, 4L);
        linkDAO.delete(ID);
        assertEquals(null, linkDAO.findAllByMaterialId(4L));
    }



}