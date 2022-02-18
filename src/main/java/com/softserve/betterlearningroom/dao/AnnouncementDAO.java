package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Announcement;

import java.util.List;

public interface AnnouncementDAO {
    List<Announcement> findByCourseId(String courseId);

    Announcement findById(Long id);

    Announcement save(Announcement announcement);

    Announcement update(Announcement updatedAnnouncement);

    void delete(Long id);
}
