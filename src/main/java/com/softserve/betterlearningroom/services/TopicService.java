package com.softserve.betterlearningroom.services;

import com.softserve.betterlearningroom.dto.TopicDTO;
import com.softserve.betterlearningroom.entity.Topic;
import com.softserve.betterlearningroom.repository.TopicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {

    @Autowired
    private TopicDAO topicDAO;

    public Topic findById(Integer id) {
        return topicDAO.findById(id).orElseThrow(() -> new RuntimeException("topic by id is not saved"));
    }

    public void removeById(Integer id) {
        topicDAO.removeById(id);
    }


    public List<TopicDTO> findAll() {
        return topicDAO.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void save(TopicDTO topicDTO) {
        topicDAO.save(toEntity(topicDTO));
    }

    public void update(TopicDTO topicDTO) {
        topicDAO.update(toEntity(topicDTO));
    }

    public Topic toEntity(TopicDTO topicDTO) {
        return Topic.builder()
                .topic_id(topicDTO.getId())
                .title(topicDTO.getTitle())
                .classroom_id(topicDTO.getClassroomId())
                .build();

    }

    public TopicDTO toDTO(Topic topic) {
        return TopicDTO.builder()
                .id(topic.getTopic_id())
                .title(topic.getTitle())
                .classroomId(topic.getClassroom_id())
                .build();

    }

}
