package com.softserve.betterlearningroom.controller;


import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
@Slf4j
public class CommentController {
    private CommentService commentService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('comment:read')")
    public ResponseEntity<CommentDTO> findById(@PathVariable Long id) {
        log.info("Loaded information about comment with id " + id);
        return ResponseEntity.ok(commentService.findByCommentId(id));
    }

    @PostMapping("/users/{userId}")
    @PreAuthorize("hasAuthority('comment:write')")
    public ResponseEntity<CommentDTO> save(@RequestBody CommentDTO commentDTO, @PathVariable Long userId) {
        commentDTO.setAuthorId(userId);
        log.info("New comment has been created");
        return new ResponseEntity<>(commentService.save(commentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        log.info("Updated comment with id " + id);
        return ResponseEntity.ok(commentService.update(commentDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDTO> delete(@PathVariable Long id) {
        commentService.delete(id);
        log.info("Deleted comment with id " + id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/materials/{materialId}")
    @PreAuthorize("hasAuthority('comment:read')")
    public ResponseEntity<List<CommentDTO>> findByMaterialId(@PathVariable Long materialId) {
        log.info("Loaded all comments with materialId " + materialId);
        return ResponseEntity.ok(commentService.findByMaterialId(materialId));
    }

    @GetMapping("/announcements/{announcementId}")
    @PreAuthorize("hasAuthority('comment:read')")
    public ResponseEntity<List<CommentDTO>> findByAnnouncementId(@PathVariable Long announcementId) {
        log.info("Loaded all comments with announcementId " + announcementId);
        return ResponseEntity.ok(commentService.findByAnnouncementId(announcementId));
    }

    @GetMapping("/user-assignments/{userAssignmentId}")
    @PreAuthorize("hasAuthority('comment:read')")
    public ResponseEntity<List<CommentDTO>> findByUserAssignmentId(@PathVariable Long userAssignmentId) {
        log.info("Loaded all comments with userAssignmentId " + userAssignmentId);
        return ResponseEntity.ok(commentService.findByUserAssignmentId(userAssignmentId));
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAuthority('comment:read')")
    public ResponseEntity<List<CommentDTO>> findByAuthorId(@PathVariable Long userId) {
        log.info("Loaded all comments with userId " + userId);
        return ResponseEntity.ok(commentService.findByAuthorId(userId));
    }
}