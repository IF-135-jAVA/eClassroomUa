package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestConfig;
import com.softserve.betterlearningroom.dao.impl.TopicDAO;
import com.softserve.betterlearningroom.entity.Announcement;
import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.Link;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.MaterialType;
import com.softserve.betterlearningroom.entity.Topic;
import com.softserve.betterlearningroom.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MaterialDao.class, TestConfig.class})
public class MaterialDaoTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private TopicDAO topicDAO;

    @Autowired
    private ClassroomDAO classroomDAO;

    private Material material;

    @BeforeAll
    static void beforeAll(){

    }

    @Test
    @Rollback(true)
    public void checkSaveAndFindById() {
        Classroom classroom = new Classroom(0L, 0L,
                "title", "session", "description",
                "code", new ArrayList<User>(), new ArrayList<User>(),
                new ArrayList<Topic>(), new ArrayList<Announcement>());
        Topic topic = new Topic(0,"title", 0);
        material = new Material(0L, MaterialType.TASK, "title0",
                "text0", new ArrayList<Link>(), 0L, 0L);
        classroomDAO.createClassroom(classroom);
        topicDAO.save(topic);
        materialDao.create(material, material.getTopicId());
        assertEquals(material, materialDao.readById(material.getId()));
    }

}
