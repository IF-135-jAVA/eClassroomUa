package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.AnnouncementDAO;
import com.softserve.betterlearningroom.dto.AnnouncementDTO;
import com.softserve.betterlearningroom.entity.Announcement;
import com.softserve.betterlearningroom.mapper.AnnouncementMapper;
import com.softserve.betterlearningroom.service.impl.AnnouncementServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(value = {MockitoExtension.class})
class AnnouncementServiceTest {

    private static final long ANNOUNCEMENT_ID = 1;

    private static final long COURSE_ID = 1;

    private static final String ANNOUNCEMENT_TEXT = "text1";

    private static final boolean ANNOUNCEMENT_ENABLED = true;


    @Mock
    private AnnouncementDAO announcementDAO;
    private AnnouncementServiceImpl announcementService;
    private AnnouncementMapper announcementMapper;

    @BeforeEach
    void setUp() {
        announcementMapper = new AnnouncementMapper();
        announcementService = new AnnouncementServiceImpl(announcementDAO, announcementMapper);
    }

    @Test
    void readByIdTest() {
        Announcement announcement = prepareAnnouncementDTO();
        given(announcementDAO.readById(ANNOUNCEMENT_ID)).willReturn(announcement);
        AnnouncementDTO announcementDTO = announcementService.readById(ANNOUNCEMENT_ID);
        assertNotNull(announcementDTO);
        assertEquals(ANNOUNCEMENT_TEXT, announcementDTO.getText());
        verify(announcementDAO).readById(ANNOUNCEMENT_ID);
    }

    @Test
    void createAnnouncementTest() {
        Announcement announcement = prepareAnnouncementDTO();
        given(announcementDAO.create(any(Announcement.class))).willReturn(announcement);
        AnnouncementDTO announcementDTO = announcementService
                .create(announcementMapper.announcementToAnnouncementDTO(announcement));
        assertEquals("text1", announcementDTO.getText());
    }


    @Test
    void readByCourseId() {
        List<Announcement> announcementList = new ArrayList<Announcement>();
        announcementList.add(new Announcement(ANNOUNCEMENT_ID, COURSE_ID, ANNOUNCEMENT_TEXT, List.of(), ANNOUNCEMENT_ENABLED));
        announcementList.add(new Announcement(2, 1, "text2", List.of(), ANNOUNCEMENT_ENABLED));
        announcementList.add(new Announcement(3, 3, "text3", List.of(), ANNOUNCEMENT_ENABLED));
        announcementList.add(new Announcement(4, 2, "text4", List.of(), ANNOUNCEMENT_ENABLED));
        given(announcementDAO.readByCourseId(3)).willReturn(announcementList);
        List<AnnouncementDTO> actualAnnouncements = announcementService.readByCourseId(3);
        assertEquals(4, actualAnnouncements.size());
        assertEquals("text3", actualAnnouncements.get(2).getText());
        verify(announcementDAO).readByCourseId(3);
    }

    @Test
    void updateCommentsTest() {

    }

    @Test
    void deleteCommentsTest() {
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(announcementDAO);
    }

    private Announcement prepareAnnouncementDTO() {
        return Announcement.builder()
                .id(1)
                .courseId(2)
                .comments(List.of())
                .text("text1")
                .enabled(true)
                .build();
    }
}


