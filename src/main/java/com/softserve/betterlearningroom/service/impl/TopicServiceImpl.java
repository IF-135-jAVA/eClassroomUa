package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.impl.TopicDaoImpl;
import com.softserve.betterlearningroom.dto.TopicDTO;
import com.softserve.betterlearningroom.mapper.TopicMapper;
import com.softserve.betterlearningroom.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDaoImpl topicDaoImpl;

    @Override
    public TopicDTO findById(Long id) {

        return TopicMapper.toDTO(topicDaoImpl.findById(id));
    }

    @Override
    public void removeById(Long id) {

        topicDaoImpl.removeById(id);
    }

    @Override
    public List<TopicDTO> findAll() {

        return topicDaoImpl.findAll().stream().map(TopicMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TopicDTO save(TopicDTO topicDTO) {

        return TopicMapper.toDTO(topicDaoImpl.save(TopicMapper.toEntity(topicDTO)));
    }

    @Override
    public TopicDTO update(TopicDTO topicDTO) {

        return TopicMapper.toDTO(topicDaoImpl.update(TopicMapper.toEntity(topicDTO)));
    }

    @Override
    public List<TopicDTO> findAllByClassroomId(Long classroomId) {

        return topicDaoImpl.findAllByClassroom(classroomId).stream().map(TopicMapper::toDTO).collect(Collectors.toList());
    }




}
