package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.AnnouncementDTO;

import java.util.List;

public interface AnnouncementService {
    /**
     * Create a new resource (announcement) in database
     *
     * @param announcementDTO AnnouncementDTO
     * @return new announcement
     */
    AnnouncementDTO save(AnnouncementDTO announcementDTO);

    /**
     * get all announcements by classroom id from the database
     *
     * @param courseId Long
     * @return List Announcement by classroom id
     */
    List<AnnouncementDTO> findByCourseId(Long courseId);

    /**
     * get announcement by id from the database
     *
     * @param id Long
     * @return announcement by id
     */
    AnnouncementDTO findById(Long id);

    /**
     * update announcement by id in the database
     *
     * @param announcementDTO AnnouncementDTO
     * @param id              Long
     * @return updated announcement
     */
    AnnouncementDTO update(AnnouncementDTO announcementDTO, Long id);


    /**
     * delete announcement by id, do it not active in the database
     *
     * @param id Long
     */
    void delete(Long id);
}
