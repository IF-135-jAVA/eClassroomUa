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

    /**
     * Create a new resource (announcement) in database
     *
     * @param announcementDTO
     * @return new announcement
     */
    @Override
    public AnnouncementDTO create(AnnouncementDTO announcementDTO) {
        announcementDTO.setText(announcementDTO.getText());
        announcementDTO.setEnabled(true);
        return announcementMapper.announcementToAnnouncementDTO(
                announcementDAO.create(announcementMapper.announcementDTOToAnnouncement(announcementDTO)));
    }

    /**
     * get all announcement by classroom id from the database
     *
     * @param courseId
     * @return List<Announcement> by classroom id
     */
    @Override
    public List<AnnouncementDTO> readByCourseId(long courseId) {
        return announcementDAO.readByCourseId(courseId)
                .stream()
                .map(announcementMapper::announcementToAnnouncementDTO)
                .collect(Collectors.toList());
    }

    /**
     * get announcement by id from the database
     *
     * @param id
     * @return announcement by id
     */
    @Override
    public AnnouncementDTO readById(long id) {
        return announcementMapper.announcementToAnnouncementDTO(announcementDAO.readById(id));
    }

    /**
     * update announcement by id in the database
     *
     * @param announcementDTO
     * @param id
     * @return updated announcement
     */
    @Override
    public AnnouncementDTO update(AnnouncementDTO announcementDTO, long id) {
        AnnouncementDTO oldAnnouncementDTO = readById(id);
        oldAnnouncementDTO.setText(announcementDTO.getText());
        return announcementMapper.announcementToAnnouncementDTO(
                announcementDAO.update(announcementMapper.announcementDTOToAnnouncement(oldAnnouncementDTO)));
    }

    /**
     * delete announcement by id, do it not active in the database
     *
     * @param id
     */
    @Override
    public void delete(long id) {
        announcementDAO.delete(id);
    }
}













