package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.AnnouncementDAO;
import com.softserve.betterlearningroom.dto.AnnouncementDTO;
import com.softserve.betterlearningroom.mapper.AnnouncementMapper;
import com.softserve.betterlearningroom.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    private AnnouncementDAO announcementDAO;
    private AnnouncementMapper announcementMapper;

    @Override
    public AnnouncementDTO save(AnnouncementDTO announcementDTO) {
        announcementDTO.setText(announcementDTO.getText());
        announcementDTO.setEnabled(true);
        return announcementMapper.announcementToAnnouncementDTO(
                announcementDAO.save(announcementMapper.announcementDTOToAnnouncement(announcementDTO)));
    }

    @Override
    public List<AnnouncementDTO> findByCourseId(String courseId) {
        return announcementDAO.findByCourseId(courseId)
                .stream()
                .map(announcementMapper::announcementToAnnouncementDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AnnouncementDTO findById(Long id) {
        return announcementMapper.announcementToAnnouncementDTO(announcementDAO.findById(id));
    }

    @Override
    public AnnouncementDTO update(AnnouncementDTO announcementDTO, Long id) {
        AnnouncementDTO oldAnnouncementDTO = findById(id);
        oldAnnouncementDTO.setText(announcementDTO.getText());
        return announcementMapper.announcementToAnnouncementDTO(
                announcementDAO.update(announcementMapper.announcementDTOToAnnouncement(oldAnnouncementDTO)));
    }

    @Override
    public void delete(Long id) {
        announcementDAO.delete(id);
    }
}













