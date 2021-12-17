package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.AnnouncementDTO;

import com.softserve.betterlearningroom.entity.Announcement;

import org.springframework.stereotype.Component;

@Component
public class AnnouncementMapper {

    public static AnnouncementDTO announcementToAnnouncementDTO(Announcement announcement) {
        AnnouncementDTO announcementDTO = new AnnouncementDTO();
        announcementDTO.setId(announcement.getId());
        announcementDTO.setCourse_id(announcement.getCourse_id());
        announcementDTO.setText(announcement.getText());
        announcementDTO.setComments(announcement.getComments());

        return announcementDTO;
    }

    public Announcement announcementDTOToAnnouncement(AnnouncementDTO announcementDTO) {
        Announcement announcement = new Announcement();
        announcement.setId(announcementDTO.getId());
        announcement.setCourse_id(announcementDTO.getCourse_id());
        announcement.setText(announcementDTO.getText());
        announcement.setComments(announcementDTO.getComments());

        return announcement;
    }

}


