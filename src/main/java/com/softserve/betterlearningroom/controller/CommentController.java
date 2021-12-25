package com.softserve.betterlearningroom.controller;


import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(
//        allowCredentials = "true",
//        origins = "http://localhost:4200",
//        allowedHeaders = "*",
//        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
//)
//@AllArgsConstructor
@RequestMapping("/api/classrooms/{classroomId}/comments")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CommentDTO commentDTO) {
        commentService.create(commentDTO);
        // TODO: 25.12.2021 return created object, not only HTTP status
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> readAll(@PathVariable int classroomId) {
        // TODO: 25.12.2021 use classroomId to get all the comments for a classroom
        List<CommentDTO> comments = commentService.readAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> readById(@PathVariable int id) {
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

