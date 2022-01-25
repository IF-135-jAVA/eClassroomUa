package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.impl.TopicDAOImpl;
import com.softserve.betterlearningroom.entity.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {TestDBConfiguration.class, TopicDAOImpl.class})
class TopicDAOTest {
    private static final Long TOPIC_ID = 3L;
    private static final long CLASSROOM_ID = 3;
    private static final String TITLE = "Mathematics";

    @Autowired
    private TopicDAO topicDao;

    @Test
    void testSaveAndGet() {
        Topic topicForSave = Topic.builder()
                .topicId(TOPIC_ID)
                .classroomId(CLASSROOM_ID)
                .title(TITLE)
                .build();

        topicDao.save(topicForSave);

        Topic byId = topicDao.findById(TOPIC_ID);
        assertNotNull(byId);
        assertEquals(TITLE, byId.getTitle());
        assertEquals(CLASSROOM_ID, byId.getClassroomId());
    }

    @Test
    void testfindAll() {
        Topic topicForSave = Topic.builder()
                .topicId(4L)
                .classroomId(2L)
                .title(TITLE)
                .build();

        topicDao.save(topicForSave);

        List<Topic> expectedtopics = topicDao.findAll();

        assertEquals(4, expectedtopics.size());
        Topic expectedtopic = expectedtopics.stream().filter(topic -> topic.getTopicId() == 3L).findFirst().orElse(null);
        assertNotNull(expectedtopic);
        assertEquals(TITLE, expectedtopic.getTitle());
        assertEquals(TOPIC_ID, expectedtopic.getClassroomId());
    }

    @Test
    void testUpdate() {
        Topic topicForSave = Topic.builder()
                .topicId(3L)
                .classroomId(2L)
                .title("test title")
                .build();

        topicDao.update(topicForSave);

        Topic updatedtopic = topicDao.findById(3L);
        assertNotNull(updatedtopic);
        assertEquals("test title", updatedtopic.getTitle());

        assertEquals(2, updatedtopic.getClassroomId());
    }
}