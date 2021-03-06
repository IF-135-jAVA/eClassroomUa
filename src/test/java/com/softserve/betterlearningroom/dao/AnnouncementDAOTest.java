//package com.softserve.betterlearningroom.dao;
//
//import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
//import com.softserve.betterlearningroom.dao.impl.AnnouncementDAOImpl;
//import com.softserve.betterlearningroom.entity.Announcement;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest(classes = {TestDBConfiguration.class, AnnouncementDAOImpl.class})
//class AnnouncementDAOTest {
//
//    @Autowired
//    private AnnouncementDAO announcementDAO;
//
//    @Test
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
//    void readByIdAnnouncementTest() {
//        Announcement announcement = prepareAnnouncementDTO();
//        assertEquals((announcement), announcementDAO.findById(1L));
//    }
//
//    @Test
//    void readByCourseIdAnnouncementTest() {
//        List<Announcement> announcementList = new ArrayList<>();
//        announcementList.add(prepareAnnouncementDTO());
//        assertEquals((announcementList), announcementDAO.findByCourseId(2L));
//    }
//
//    @Test
//    void createAnnouncementTest() {
//        Announcement announcement = prepareAnnouncementDTO();
//        Announcement savedAnnouncement = announcementDAO.save(announcement);
//        assertNotNull(savedAnnouncement);
//        assertEquals("text1", savedAnnouncement.getText());
//        assertEquals(2L, savedAnnouncement.getCourseId());
//    }
//
//    @Test
//    void updateAnnouncementTest() {
//        Announcement announcement = prepareAnnouncementDTO();
//        announcement.setId(2);
//        announcementDAO.update(announcement);
//        assertEquals("text1", announcementDAO.findById(2L).getText());
//    }
//
//    @Test
//    void deleteAnnouncementTest() {
//        Announcement announcement = prepareAnnouncementDTO();
//        announcementDAO.delete(announcement.getId());
//    }
//
//    private Announcement prepareAnnouncementDTO() {
//        return Announcement.builder()
//                .id(1L)
//                .courseId(2L)
//                .text("text1")
//                .enabled(true)
//                .build();
//    }
//}
