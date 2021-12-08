package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.AnnouncementDAO;
import com.softserve.betterlearningroom.entity.Announcement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AnnouncementService {
    private AnnouncementDAO announcementDAO;

    public void create(Announcement announcement) {
        announcement.setText(announcement.getText());
        announcement.setComments(announcement.getComments());
        announcementDAO.create(announcement);
    }
    public List<Announcement> readAll() {
        List<Announcement> announcements = announcementDAO.readAll();
        return announcements.isEmpty() ? new ArrayList<>() : announcements;
    }
    public Announcement readById(long id) {
        List<Announcement> result = announcementDAO.readById(id);

        return result.isEmpty() ? null : result.get(0);
    }

    public void update(Announcement announcement, long id) {
        Announcement oldAnnouncement = readById(id);
        if (oldAnnouncement != null) {
            oldAnnouncement.setText(announcement.getText());
            oldAnnouncement.setComments(announcement.getComments());
            announcementDAO.update(oldAnnouncement);
        }
    }

    public void delete(long id) {
        Announcement announcement = readById(id);
        if (announcement != null) {

            announcementDAO.delete(announcement.getId());

        }

    }
}
