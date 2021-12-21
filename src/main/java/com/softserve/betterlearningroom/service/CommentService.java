package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class
CommentService {
    private CommentDAO commentDAO;
    private CommentMapper commentMapper;

    public void createMaterialComments(CommentDTO commentDTO) {
        commentDAO.createMaterialComments(commentMapper.commentDTOToComment(commentDTO));
    }

    public List<CommentDTO> readAllMaterialComments() {
        return commentDAO.readAllMaterialComments().stream()
                .map(CommentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }



    public CommentDTO readByIdMaterialComments(long id) {
        Comment comment = commentDAO.readByIdMaterialComments(id);

        return commentMapper.commentToCommentDTO(comment);
    }


    public void updateMaterialComments(CommentDTO commentDTO, long id) {
        commentDAO.updateMaterialComments(commentMapper.commentDTOToComment(commentDTO));
    }


    public void deleteMaterialComments(long id) {
        CommentDTO commentDTO = readByIdMaterialComments(id);
            commentDAO.deleteMaterialComments(commentDTO.getId());
        }




    public void createAnnouncementComments(CommentDTO commentDTO) {
        commentDAO.createAnnouncementComments(commentMapper.commentDTOToComment(commentDTO));
    }

    public List<CommentDTO> readAllAnnouncementComments() {
        return commentDAO.readAllAnnouncementComments().stream()
                .map(CommentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }



    public CommentDTO readByIdAnnouncementComments(long id) {
        Comment comment = commentDAO.readByIdAnnouncementComments(id);

        return commentMapper.commentToCommentDTO(comment);
    }


    public void updateAnnouncementComments(CommentDTO commentDTO, long id) {
        commentDAO.updateAnnouncementComments(commentMapper.commentDTOToComment(commentDTO));
    }


    public void deleteAnnouncementComments(long id) {
        CommentDTO commentDTO = readByIdAnnouncementComments(id);
        commentDAO.deleteAnnouncementComments(commentDTO.getId());
    }

    }

