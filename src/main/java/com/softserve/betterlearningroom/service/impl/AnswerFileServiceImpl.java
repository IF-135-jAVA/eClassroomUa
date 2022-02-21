package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.configuration.AmazonConfiguration;
import com.softserve.betterlearningroom.dao.AnswerFileDAO;
import com.softserve.betterlearningroom.dto.AnswerFileDTO;
import com.softserve.betterlearningroom.entity.AnswerFile;
import com.softserve.betterlearningroom.mapper.AnswerFileMapper;
import com.softserve.betterlearningroom.service.AnswerFileService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private AnswerFileMapper answerFileMapper = Mappers.getMapper(AnswerFileMapper.class);

    @Override
    public AnswerFileDTO save(Long userAssignmentId, MultipartFile file) {
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
        return answerFileMapper.answerFileToAnswerFileDTO(answerFileDAO.save(new AnswerFile(0, userAssignmentId, path, fileName)));
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
}
