package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.dto.AnnouncementDTO;
import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.entity.Announcement;
import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.mapper.AnnouncementMapper;
import com.softserve.betterlearningroom.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentDAO commentDAO;
    private CommentMapper commentMapper;

    public void create(CommentDTO commentDTO) {
        commentDAO.create(commentMapper.commentDTOToComment(commentDTO));
    }

    public List<CommentDTO> readAll() {
        return commentDAO.readAll().stream()
                .map(CommentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }



    public CommentDTO readById(long id) {
        Comment comment = commentDAO.readById(id);

        return commentMapper.commentToCommentDTO(comment);
    }


    public void update(CommentDTO commentDTO, long id) {
        commentDAO.update(commentMapper.commentDTOToComment(commentDTO));
    }


    public void delete(long id) {
        CommentDTO commentDTO = readById(id);
            commentDAO.delete(commentDTO.getId());
        }

    }

