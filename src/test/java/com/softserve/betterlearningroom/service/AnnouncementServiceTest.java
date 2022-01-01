package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.dto.AnnouncementDTO;
import com.softserve.betterlearningroom.dao.AnnouncementDAO;
import com.softserve.betterlearningroom.entity.Announcement;
import com.softserve.betterlearningroom.mapper.AnnouncementMapper;
import com.softserve.betterlearningroom.mapper.CommentMapper;
import com.softserve.betterlearningroom.service.impl.AnnouncementServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataRetrievalFailureException;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class AnnouncementServiceTest {

    @Mock
    private AnnouncementDAO announcementDAO;
    private AnnouncementServiceImpl announcementService;
    private AnnouncementMapper announcementMapper;
    private Announcement announcement;
    private CommentDAO commentDAO;
    private CommentMapper commentMapper;

    @Before
    public void setUp() {
        announcementMapper = new AnnouncementMapper();
        announcementService = new AnnouncementServiceImpl(announcementDAO, announcementMapper, commentDAO, commentMapper);
        announcement = new Announcement(1, 2, "text", true);
    }

    @Test
    public void createAnnouncementTest() {
        given(announcementDAO.create(any(Announcement.class))).willReturn(announcement);
        AnnouncementDTO announcementDTO = announcementService.create(announcementMapper.announcementToAnnouncementDTO(announcement));
        assertEquals("text", announcementDTO.getText());
    }

    @Test
    public void addComment() {
    }

    @Test
    public void readByCourseId() {
        List<Announcement> announcementList = new ArrayList<Announcement>();
        announcementList.add(new Announcement(1, 2, "text1", true));
        announcementList.add(new Announcement(2, 3, "text2", true));
        announcementList.add(new Announcement(3, 4, "text3", true));
        announcementList.add(new Announcement(4, 1, "text4", true));
        given(announcementDAO.readByCourseId(3)).willReturn(announcementList);
        List<AnnouncementDTO> actualAnnouncement = announcementService.readByCourseId(3);
        assertEquals(4, actualAnnouncement.size());
        assertEquals("text1", actualAnnouncement.get(0).getText());
    }

    @Test
    public void readById() {
        given(announcementDAO.readById(1)).willReturn(announcement);
        AnnouncementDTO announcementDTO = announcementService.readById(1);
        assertEquals(announcementDTO.getId(), 1);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}