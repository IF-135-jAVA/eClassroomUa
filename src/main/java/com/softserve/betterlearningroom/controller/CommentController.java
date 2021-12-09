package com.softserve.betterlearningroom.controller;


import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.service.CommentService;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Comment comment) {
        commentService.create(comment);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> readById(@PathVariable long id) {
        Comment result = commentService.readById(id);
        if(result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody Comment comment) {
        if (commentService.readById(id) == null) return ResponseEntity.notFound().build();
        commentService.update(comment, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        if(commentService.readById(id) == null) return ResponseEntity.notFound().build();
        commentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Comment> readAll() {
        List<Comment> comments = commentService.readAll();
        return comments.isEmpty() ? new ArrayList<>() : comments;
    }
}
