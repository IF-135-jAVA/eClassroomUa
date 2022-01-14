package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.configuration.TestDBConfiguration1;
import com.softserve.betterlearningroom.dao.impl.AnnouncementDAOImpl;
import com.softserve.betterlearningroom.entity.Announcement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = {TestDBConfiguration1.class, AnnouncementDAOImpl.class})
class AnnouncementDAOTest {

    @Autowired
    private AnnouncementDAO announcementDAO;

    @Test
    void readByIdAnnouncementTest() {
        Announcement announcement = prepareAnnouncementDTO();
        assertEquals((announcement), announcementDAO.readById(1));
    }

    @Test
    void readByCourseIdAnnouncementTest() {
        List<Announcement> announcementList = new ArrayList<>();
        announcementList.add(prepareAnnouncementDTO());
        assertEquals((announcementList), announcementDAO.readByCourseId(2));
    }

    @Test
    void createAnnouncementTest() {
        Announcement announcement = prepareAnnouncementDTO();
        Announcement savedAnnouncement = announcementDAO.create(announcement);
        assertNotNull(savedAnnouncement);
        assertEquals("text1", savedAnnouncement.getText());
        assertEquals(2, savedAnnouncement.getCourseId());
    }

    @Test
    void updateAnnouncementTest() {
        Announcement announcement = prepareAnnouncementDTO();
        announcement.setId(2);
        announcementDAO.update(announcement);
        assertEquals("text1", announcementDAO.readById(2).getText());
    }

    @Test
    void deleteAnnouncementTest() {
        Announcement announcement = prepareAnnouncementDTO();
        announcementDAO.delete(announcement.getId());
    }

    private Announcement prepareAnnouncementDTO() {
        return Announcement.builder()
                .id(1)
                .courseId(2)
                .text("text1")
                .enabled(true)
                .build();
    }
}
