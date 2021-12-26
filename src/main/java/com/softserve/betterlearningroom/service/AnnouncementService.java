package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.AnnouncementDAO;
import com.softserve.betterlearningroom.dto.AnnouncementDTO;
import com.softserve.betterlearningroom.entity.Announcement;
import com.softserve.betterlearningroom.mapper.AnnouncementMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnnouncementService {
    private AnnouncementDAO announcementDAO;
    private AnnouncementMapper announcementMapper;

    public void create(AnnouncementDTO announcementDTO) {
        announcementDAO.create(announcementMapper.announcementDTOToAnnouncement(announcementDTO));
    }

    public List<AnnouncementDTO> readByCourseId(long courseId) {
        return announcementDAO.readByCourseId(courseId)
                .stream()
                .map(announcementMapper::announcementToAnnouncementDTO)
                .collect(Collectors.toList());
    }

    public AnnouncementDTO readById(long id) {
        List<Announcement> result = announcementDAO.readById(id);
        return result.isEmpty() ? null : announcementMapper.announcementToAnnouncementDTO(result.get(0));
    }

    public void update(AnnouncementDTO announcementDTO, long id) {
        AnnouncementDTO oldAnnouncementDTO = readById(id);
        if (oldAnnouncementDTO != null) {
            oldAnnouncementDTO.setText(announcementDTO.getText());
            announcementDAO.update(announcementMapper.announcementDTOToAnnouncement(oldAnnouncementDTO));
        }
    }

    public void delete(long id) {
        AnnouncementDTO announcementDTO = readById(id);
        announcementDAO.delete(announcementDTO.getId());
    }
}

