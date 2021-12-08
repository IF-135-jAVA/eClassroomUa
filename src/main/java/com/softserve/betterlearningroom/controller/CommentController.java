package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.entity.Announcement;
import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.service.AnnouncementService;
import com.softserve.betterlearningroom.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/comments")
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

    @GetMapping("{id}")
    public List<Comment> readAll() {
        List<Comment> comments = commentService.readAll();
        return comments.isEmpty() ? new ArrayList<>() : comments;
    }
}
