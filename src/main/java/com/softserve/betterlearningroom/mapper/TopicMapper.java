package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.TopicDTO;
import com.softserve.betterlearningroom.entity.Topic;

public class TopicMapper {

    public static Topic toEntity(TopicDTO topicDTO) {

        return Topic.builder()
                .topicId(topicDTO.getId())
                .title(topicDTO.getTitle())
                .classroomId(topicDTO.getClassroomId())
                .build();

    }

    public static TopicDTO toDTO(Topic topic) {
        return TopicDTO.builder()
                .id(topic.getTopicId())
                .title(topic.getTitle())
                .classroomId(topic.getClassroomId())
                .build();

    }
}
