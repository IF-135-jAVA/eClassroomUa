package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.TopicDTO;

import java.util.List;

public interface TopicService {

    TopicDTO findById(Long id);

    void delete(Long id);

    List<TopicDTO> findAll();

    TopicDTO save(TopicDTO topicDTO);

    TopicDTO update(TopicDTO topicDTO);

    List<TopicDTO> findAllByClassroomId(String classroomId);

}
