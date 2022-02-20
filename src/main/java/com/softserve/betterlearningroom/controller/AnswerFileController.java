package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.AnswerFileDTO;
import com.softserve.betterlearningroom.service.AnswerFileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/assignments/{userAssignmentId}/files")
@AllArgsConstructor
public class AnswerFileController {
    private AnswerFileService answerFileService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@userAssignmentServiceImpl.findById(#userAssignmentId).userId.equals(authentication.principal.getId())")
    public ResponseEntity<AnswerFileDTO> save(@PathVariable long userAssignmentId, @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(answerFileService.save(userAssignmentId, file), HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @PreAuthorize("hasRole('TEACHER') or @userAssignmentServiceImpl.findById(@answerFileDAOImpl.findById(#id).userAssignmentId).userId.equals(authentication.principal.getId())")
    public ResponseEntity<byte[]> findById(@PathVariable Long id) {
        return ResponseEntity.ok(answerFileService.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('TEACHER') or @userAssignmentServiceImpl.findById(#userAssignmentId).userId.equals(authentication.principal.getId())")
    public ResponseEntity<List<AnswerFileDTO>> findByUserAssignmentId(@PathVariable Long userAssignmentId) {
        return ResponseEntity.ok(answerFileService.findByUserAssignmentId(userAssignmentId));
    }
}
