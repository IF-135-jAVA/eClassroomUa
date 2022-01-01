package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Announcement;

import java.util.List;

public interface AnnouncementDAO {
    List<Announcement> readByCourseId(long courseId);

    Announcement readById(long id);

    Announcement create(Announcement announcement);

    Announcement update(Announcement updateAnnouncement);

    void delete(long id);
}
