package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.configuration.AmazonConfiguration;
import com.softserve.betterlearningroom.dao.AnswerFileDAO;
import com.softserve.betterlearningroom.dao.UserAssignmentDAO;
import com.softserve.betterlearningroom.dto.AnswerFileDTO;
import com.softserve.betterlearningroom.entity.AnswerFile;
import com.softserve.betterlearningroom.entity.UserAssignment;
import com.softserve.betterlearningroom.exception.SubmissionNotAllowedException;
import com.softserve.betterlearningroom.mapper.AnswerFileMapper;
import com.softserve.betterlearningroom.service.AnswerFileService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerFileServiceImpl implements AnswerFileService {
    private final FileStoreService fileStoreService;
    private final AnswerFileDAO answerFileDAO;
    private final UserAssignmentDAO userAssignmentDao;
    private AnswerFileMapper answerFileMapper = Mappers.getMapper(AnswerFileMapper.class);

    @Override
    public AnswerFileDTO save(Long userAssignmentId, MultipartFile file) {
        UserAssignment userAssignment;
        try {
            userAssignment = userAssignmentDao.findById(userAssignmentId);
        } catch (DataRetrievalFailureException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
        checkIfSubmissionAllowed(userAssignment);
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        String path = AmazonConfiguration.BUCKET_NAME + "/" + UUID.randomUUID();
        String fileName = file.getOriginalFilename();
        try {
            fileStoreService.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        AnswerFileDTO result = answerFileMapper.answerFileToAnswerFileDTO(answerFileDAO.save(new AnswerFile(0, userAssignmentId, path, fileName)));
        renewUserAssignmentSubmissionDate(userAssignment);
        return result;
    }

    @Override
    public byte[] findById(Long id) {
        AnswerFile answerFile = answerFileDAO.findById(id);
        return fileStoreService.download(answerFile.getPath(), answerFile.getFileName());
    }

    @Override
    public List<AnswerFileDTO> findByUserAssignmentId(Long userAssignmentId) {
        return answerFileDAO.findByUserAssignmentId(userAssignmentId)
                .stream()
                .map(answerFileMapper::answerFileToAnswerFileDTO)
                .collect(Collectors.toList());
    }

    private void renewUserAssignmentSubmissionDate(UserAssignment userAssignment) {
        userAssignment.setSubmissionDate(LocalDateTime.now());
        userAssignmentDao.update(userAssignment);
    }

    private void checkIfSubmissionAllowed(UserAssignment userAssignment) {
        LocalDateTime dueDate = userAssignment.getDueDate();
        if (dueDate != null && LocalDateTime.now().isAfter(dueDate)) {
            throw new SubmissionNotAllowedException("Due date for assignment with id - " + userAssignment.getMaterialId() + " has passed. Due date is " + dueDate + ".");
        }
    }
}
