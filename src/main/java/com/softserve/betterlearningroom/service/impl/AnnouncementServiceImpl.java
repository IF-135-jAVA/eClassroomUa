package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.AnnouncementDAO;
import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.dto.AnnouncementDTO;
import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.entity.Announcement;
import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.mapper.AnnouncementMapper;
import com.softserve.betterlearningroom.mapper.CommentMapper;
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
    private CommentDAO commentDAO;
    private CommentMapper commentMapper;


    @Override
    public AnnouncementDTO create(AnnouncementDTO announcementDTO) {
        //announcementDTO.setText(announcementDTO.getText());
        announcementDTO.setEnabled(true);
        return announcementMapper.announcementToAnnouncementDTO(
                announcementDAO.create(announcementMapper.announcementDTOToAnnouncement(announcementDTO)));
    }

    @Override
    public AnnouncementDTO addComment(CommentDTO commentDTO) {
        commentDAO.createComment(commentMapper.commentDTOToComment(commentDTO));
        Announcement announcement = announcementDAO.readById(commentDTO.getAnnouncementId());
        List<Comment> comments = commentDAO.readByIdAnnouncementComments(announcement.getId());
        AnnouncementDTO announcementDTO = announcementMapper.announcementToAnnouncementDTO(announcement);
        announcementDTO.setComments(comments);
        return announcementDTO;
    }

    @Override
    public List<AnnouncementDTO> readByCourseId(long courseId) {
        return announcementDAO.readByCourseId(courseId)
                .stream()
                .map(announcementMapper::announcementToAnnouncementDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AnnouncementDTO readById(long id) {
        return announcementMapper.announcementToAnnouncementDTO(announcementDAO.readById(id));
    }

    @Override
    public AnnouncementDTO update(AnnouncementDTO announcementDTO, long id) {
        AnnouncementDTO oldAnnouncementDTO = readById(id);
        oldAnnouncementDTO.setText(announcementDTO.getText());
        return announcementMapper.announcementToAnnouncementDTO(
                announcementDAO.update(announcementMapper.announcementDTOToAnnouncement(oldAnnouncementDTO)));
    }

    @Override
    public void delete(long id) {
        announcementDAO.delete(id);
    }
}













