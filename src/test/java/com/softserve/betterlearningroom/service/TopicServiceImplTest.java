package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.impl.TopicDAOImpl;
import com.softserve.betterlearningroom.dto.TopicDTO;
import com.softserve.betterlearningroom.entity.Topic;
import com.softserve.betterlearningroom.mapper.TopicMapper;
import com.softserve.betterlearningroom.service.impl.TopicServiceImpl;
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
class TopicServiceImplTest {

    @InjectMocks
    private TopicServiceImpl topicService;

    @Mock
    private TopicDAOImpl topicDaoImpl;

    private Topic expectedTopic;
    private TopicDTO expectedTopicDTO;

    @BeforeEach
    void setUp() {
        expectedTopicDTO = TopicDTO.builder()
                .id(1L)
                .classroomId(2L)
                .title("Mathematics")
                .build();
        expectedTopic = Topic.builder()
                .classroomId(2L)
                .topicId(2L)
                .title("Mathematics")
                .build();
    }

    @Test
    void testGetById() {

        when(topicDaoImpl.findById(1L)).thenReturn(expectedTopic);

        TopicDTO byId = topicService.findById(1L);

        assertNotNull(byId);
        assertEquals(expectedTopic.getClassroomId(), byId.getClassroomId());
        assertEquals(expectedTopic.getTitle(), byId.getTitle());

        verify(topicDaoImpl).findById(1L);
    }

    @Test
    void testSaveTopicDTO() {

        when(topicDaoImpl.save(any(Topic.class))).thenReturn(expectedTopic);

        topicService.save(expectedTopicDTO);
        assertNotNull(expectedTopic);
        assertEquals("Mathematics", expectedTopic.getTitle());
        System.out.println(expectedTopic);
        assertEquals(2, expectedTopic.getClassroomId());
    }

    @Test
    void testFindAll() {
        List<Topic> listExpected = new ArrayList<Topic>();
        listExpected.add(expectedTopic);
        List<TopicDTO> listToDtolistExpected = listExpected.stream().map(TopicMapper::toDTO).collect(Collectors.toList());

        when(topicDaoImpl.findAll()).thenReturn(listExpected);

        List<TopicDTO> listActual = topicService.findAll();

        assertEquals(listActual, listToDtolistExpected);
    }

    @Test
    void testUpdate() {
        when(topicDaoImpl.update(any(Topic.class))).thenReturn(expectedTopic);

        topicService.update(expectedTopicDTO);

        assertNotNull(expectedTopic);
        assertEquals("Mathematics", expectedTopic.getTitle());
        assertEquals(2, expectedTopic.getClassroomId());
    }
}