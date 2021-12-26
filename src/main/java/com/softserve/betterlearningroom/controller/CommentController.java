package com.softserve.betterlearningroom.controller;


import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.service.CommentService;
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


@RestController
@AllArgsConstructor
@RequestMapping("/api/classrooms/{classroomId}")

public class CommentController {
    private CommentService commentService;

    @GetMapping("/comments/{id}")

    public ResponseEntity<CommentDTO> readByIdComments(@PathVariable long id) {
        CommentDTO result = commentService.readByIdComments(id);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentDTO> createComments(@RequestBody CommentDTO commentDTO) {
        commentService.createComments(commentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> updateComments(@PathVariable long id, @RequestBody CommentDTO commentDTO) {
        if (commentService.readByIdComments(id) == null) return ResponseEntity.notFound().build();
        commentService.updateComments(commentDTO, id);
        return ResponseEntity.ok(commentService.readByIdComments(id));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> deleteComments(@PathVariable long id) {
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

    @GetMapping("/users/{userId}/comments")
    public ResponseEntity<List<CommentDTO>> readByIdAuthorId(@PathVariable long userId) {
        return ResponseEntity.ok(commentService.readByIdAuthorId(userId));
    }
}