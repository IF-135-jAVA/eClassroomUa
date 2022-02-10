package com.softserve.betterlearningroom.dto;

import com.softserve.betterlearningroom.entity.Announcement;
import com.softserve.betterlearningroom.entity.Topic;
import com.softserve.betterlearningroom.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassroomDTO {
    private Long classroomId;

    private Long userId;

    private String title;

    private String session;

    private String description;

    private boolean enabled;

    private User owner;

    private List<User> teachers;

    private List<User> students;

    private List<Topic> topics;

    private List<Announcement> announcements;
}
