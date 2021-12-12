package com.softserve.betterlearningroom.services;

import com.softserve.betterlearningroom.dto.TopicDTO;
import com.softserve.betterlearningroom.entity.Topic;
import com.softserve.betterlearningroom.repository.TopicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicDAO topicDAO;

    public Topic findById(Integer id) {

        return topicDAO.findById(id).orElseThrow(() -> new RuntimeException("topic by id is not saved"));
    }

    public int removeById(Integer id) {

        return topicDAO.removeById(id);
    }


    public List<Topic> findAll() {

        return topicDAO.findAll();
    }

    public void save(TopicDTO topicDTO) {

        topicDAO.save(toEntity(topicDTO));
    }

    public void update(TopicDTO topicDTO) {

        topicDAO.update(toEntity(topicDTO));
    }

    public Topic toEntity(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.builder()
                .title(topicDTO.getTitle());
        return topic;

    }

    public TopicDTO toDTO(Topic topic) {
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.builder()
                .title(topic.getTitle());
        return topicDTO;

    }

}
