package com.softserve.betterlearningroom.controller;


import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.service.CommentMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/classrooms/{classroomId}/comments_material")

public class CommentMaterialController {
    private CommentMaterialService commentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CommentDTO commentDTO){
        commentService.create(commentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> readAll() {
        List<CommentDTO> comments = commentService.readAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> readById(
            @PathVariable int id) {
        CommentDTO commentDTO = commentService.readById(id);
        return ResponseEntity.ok().body(commentDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody CommentDTO commentDTO) {
        commentService.update(commentDTO, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        commentService.delete(id);
        return ResponseEntity.ok().build();
    }

}

