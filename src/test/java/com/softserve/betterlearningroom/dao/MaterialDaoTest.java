/*
package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.BeLeRoApplication;
import com.softserve.betterlearningroom.configuration.DBConfiguration;
import com.softserve.betterlearningroom.configuration.TestConfig;
import com.softserve.betterlearningroom.dao.impl.MaterialDao;
import com.softserve.betterlearningroom.entity.Criterion;
import com.softserve.betterlearningroom.entity.Link;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.MaterialType;
import com.softserve.betterlearningroom.entity.Question;
import com.softserve.betterlearningroom.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MaterialDao.class, BeLeRoApplication.class, TestConfig.class, DBConfiguration.class})
public class MaterialDaoTest {

    @Autowired
    private MaterialDao materialDao;

    private Material material;

    @Before
    public void before() {
        material = new Material(0L, MaterialType.TASK, "title0",
                "text0", new ArrayList<Link>(), 0L, 0L, LocalDateTime.now(),
                LocalDateTime.now(), new ArrayList<Criterion>(), new ArrayList<Question>(),
                new ArrayList<User>(), 12, "task", "", "www.www.www/");
        materialDao.create(material, material.getTopicId());
    }

    @Test
    public void checkSave() {
        Material testMaterial = new Material(0L, MaterialType.TASK, "title0",
                "text0", new ArrayList<Link>(), 0L, 0L, LocalDateTime.now(),
                LocalDateTime.now(), new ArrayList<Criterion>(), new ArrayList<Question>(),
                new ArrayList<User>(), 12, "task", "", "www.www.www/");
        materialDao.create(testMaterial, testMaterial.getTopicId());
        Material newMaterial = materialDao.create(testMaterial, testMaterial.getTopicId());
        testMaterial.setId(newMaterial.getId());
        assertEquals(testMaterial, newMaterial);
    }

    @Test
    public void checkSaveAndFindById() {
        material.setId(materialDao.create(material, material.getTopicId()).getId());
        Material newMaterial = materialDao.readById(material.getId());
        assertEquals(material, newMaterial);
    }

    @Test
    public void checkSaveAndFindByName() {
        material.setId(materialDao.create(material, material.getTopicId()).getId());
        Material newMaterial = materialDao.readAllByName(0L, "text0").stream().findFirst().orElse(null);
        assertEquals(material, newMaterial);
    }

}
*/
