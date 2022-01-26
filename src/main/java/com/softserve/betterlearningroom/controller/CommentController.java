package com.softserve.betterlearningroom.controller;


import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findByCommentId(id));
    }

    @PostMapping("/users/{userId}/comments")
    public ResponseEntity<CommentDTO> save(@RequestBody CommentDTO commentDTO, @PathVariable Long userId) {
        commentDTO.setAuthorId(userId);
        return new ResponseEntity<>(commentService.save(commentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.update(commentDTO, id));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/materials/{materialId}/materialComments")
    public ResponseEntity<List<CommentDTO>> findByMaterialId(@PathVariable Long materialId) {
        return ResponseEntity.ok(commentService.findByMaterialId(materialId));
    }

    @GetMapping("/announcements/{announcementId}/announcementComments")
    public ResponseEntity<List<CommentDTO>> findByAnnouncementId(@PathVariable Long announcementId) {
        return ResponseEntity.ok(commentService.findByAnnouncementId(announcementId));
    }

    @GetMapping("/user-assignments/{userAssignmentId}/userAssignmentComments")
    public ResponseEntity<List<CommentDTO>> findByUserAssignmentId(@PathVariable Long userAssignmentId) {
        return ResponseEntity.ok(commentService.findByUserAssignmentId(userAssignmentId));
    }

    @GetMapping("/users/{userId}/comments")
    public ResponseEntity<List<CommentDTO>> findByAuthorId(@PathVariable Long userId) {
        return ResponseEntity.ok(commentService.findByAuthorId(userId));
    }
}