package com.softserve.betterlearningroom.controller;


import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/classrooms/{classroomId}")

public class CommentController {
    private CommentService commentService;

    @GetMapping("/materials/{materialId}/materialComments/{id}")
    public ResponseEntity<CommentDTO> readByIdComments(
            @PathVariable long id) {
        CommentDTO result = commentService.readByIdComments(id);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/materialComments")
    public ResponseEntity<?> createComments(@RequestBody CommentDTO commentDTO) {
        commentService.createComments(commentDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/materialComments/{id}")
    public ResponseEntity<CommentDTO> updateComments(@PathVariable long id, @RequestBody CommentDTO commentDTO) {
        if (commentService.readByIdComments(id) == null) return ResponseEntity.notFound().build();
        commentService.updateComments(commentDTO, id);
        return ResponseEntity.ok(commentService.readByIdComments(id));
    }

    @DeleteMapping("/materialComments/{id}")
    public ResponseEntity<?> deleteComments(@PathVariable long id) {
        commentService.deleteComments(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/materials/{materialId}/materialComments")
    public ResponseEntity<List<CommentDTO>> readByIdMaterialComments(@PathVariable long materialId) {
        return ResponseEntity.ok(commentService.readByIdMaterialComments(materialId));
    }

    @GetMapping("/announcements/{announcementId}/announcementComments")
    public ResponseEntity<List<CommentDTO>> readByIdAnnouncementComments(@PathVariable long announcementId) {
        return ResponseEntity.ok(commentService.readByIdAnnouncementComments(announcementId));
    }

    @GetMapping("/user-assignments/{userAssignmentId}/userAssignmentComments")
    public ResponseEntity<List<CommentDTO>> readByIdUserAssignmentComments(@PathVariable long userAssignmentId) {
        return ResponseEntity.ok(commentService.readByIdUserAssignmentComments(userAssignmentId));
    }
}