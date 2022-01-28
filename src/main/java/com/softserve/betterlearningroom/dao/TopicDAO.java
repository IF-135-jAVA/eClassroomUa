package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Topic;

import java.util.List;

public interface TopicDAO {

    Topic save(Topic topic);

    Topic update(Topic topic);

    List<Topic> findAll();

    Topic findById(Long id);

    void removeById(Long id);

    List<Topic> findAllByClassroomId(Long classroomId);





}
