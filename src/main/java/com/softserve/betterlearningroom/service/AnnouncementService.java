package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.AnnouncementDTO;


import java.util.List;

public interface AnnouncementService {
    
    AnnouncementDTO save(AnnouncementDTO announcementDTO);

    List<AnnouncementDTO> findByCourseId(Long courseId);

    AnnouncementDTO findById(Long id);

    AnnouncementDTO update(AnnouncementDTO announcementDTO, Long id);

    void delete(Long id);
}
