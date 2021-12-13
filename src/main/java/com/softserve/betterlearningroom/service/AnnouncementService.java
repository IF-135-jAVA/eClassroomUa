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

    public List<AnnouncementDTO> readAll() {
        return announcementDAO.readAll().stream()
                .map(AnnouncementMapper::announcementToAnnouncementDTO)
                .collect(Collectors.toList());
    }



    public AnnouncementDTO readById(long id) {
        Announcement announcement = announcementDAO.readById(id);

            return announcementMapper.announcementToAnnouncementDTO(announcement);
        }


    public void update(AnnouncementDTO announcementDTO, long id) {
        announcementDAO.update(announcementMapper.announcementDTOToAnnouncement(announcementDTO));
        }


    public void delete(long id) {
        AnnouncementDTO announcementDTO = readById(id);

            announcementDAO.delete(announcementDTO.getId());
        }

    }

