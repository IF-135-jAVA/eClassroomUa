package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.AnnouncementDTO;

import com.softserve.betterlearningroom.entity.Announcement;

import org.springframework.stereotype.Component;

@Component
public class AnnouncementMapper {

    public AnnouncementDTO announcementToAnnouncementDTO(Announcement announcement) {
        AnnouncementDTO announcementDTO = new AnnouncementDTO();
        announcementDTO.setId(announcement.getId());
        announcementDTO.setCourseId(announcement.getCourseId());
        announcementDTO.setText(announcement.getText());
        announcementDTO.setComments(announcement.getComments());

        return announcementDTO;
    }

    public Announcement announcementDTOToAnnouncement(AnnouncementDTO announcementDTO) {
        Announcement announcement = new Announcement();
        announcement.setId(announcementDTO.getId());
        announcement.setCourseId(announcementDTO.getCourseId());
        announcement.setText(announcementDTO.getText());
        announcement.setComments(announcementDTO.getComments());

        return announcement;
    }

}


