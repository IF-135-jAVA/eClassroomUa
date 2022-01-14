package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.AnnouncementDTO;


import java.util.List;

public interface AnnouncementService {
    AnnouncementDTO create(AnnouncementDTO announcementDTO);

    List<AnnouncementDTO> readByCourseId(long courseId);

    AnnouncementDTO readById(long id);

    AnnouncementDTO update(AnnouncementDTO announcementDTO, long id);

    void delete(long id);
}
