package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.AnswerFileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnswerFileService {

    AnswerFileDTO save(Long userAssignmentId, MultipartFile file);

    byte[] findById(Long id);

    List<AnswerFileDTO> findByUserAssignmentId(Long userAssignmentId);
}
