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

    @PostMapping("/materialComments")
    public ResponseEntity<?> createMaterialComments(@RequestBody CommentDTO commentDTO){
        commentService.createMaterialComments(commentDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/materialComments")
    public ResponseEntity<List<CommentDTO>> readAllMaterialComments() {
        List<CommentDTO> comments = commentService.readAllMaterialComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/materialComments/{id}")
    public ResponseEntity<CommentDTO> readByIdMaterialComments(
            @PathVariable long id) {
        CommentDTO commentDTO = commentService.readByIdMaterialComments(id);
        return ResponseEntity.ok().body(commentDTO);
    }

    @PutMapping("/materialComments/{id}")
    public ResponseEntity<?> updateMaterialComments(@PathVariable long id, @RequestBody CommentDTO commentDTO) {
        commentService.updateMaterialComments(commentDTO, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/materialComments/{id}")
    public ResponseEntity<?> deleteMaterialComments(@PathVariable long id) {
        commentService.deleteMaterialComments(id);
        return ResponseEntity.ok().build();
    }





    @PostMapping("/announcementComments")
    public ResponseEntity<?> createAnnouncementComments(@RequestBody CommentDTO commentDTO){
        commentService.createAnnouncementComments(commentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/announcementComments")
    public ResponseEntity<List<CommentDTO>> readAllAnnouncementComments() {
        List<CommentDTO> comments = commentService.readAllAnnouncementComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/announcementComments/{id}")
    public ResponseEntity<CommentDTO> readByIdAnnouncementComments(
            @PathVariable long id) {
        CommentDTO commentDTO = commentService.readByIdAnnouncementComments(id);
        return ResponseEntity.ok().body(commentDTO);
    }

    @PutMapping("/announcementComments/{id}")
    public ResponseEntity<?> updateAnnouncementComments(@PathVariable long id, @RequestBody CommentDTO commentDTO) {
        commentService.updateAnnouncementComments(commentDTO, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/announcementComments/{id}")
    public ResponseEntity<?> deleteAnnouncementComments(@PathVariable long id) {
        commentService.deleteAnnouncementComments(id);
        return ResponseEntity.ok().build();
    }

}

