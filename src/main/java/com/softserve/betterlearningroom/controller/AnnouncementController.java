package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.entity.Announcement;
import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.service.AnnouncementService;
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
@RequestMapping("/api/announcements")
public class AnnouncementController {
    private AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Announcement announcement) {
        announcementService.create(announcement);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Announcement> readById(@PathVariable long id) {
        Announcement result = announcementService.readById(id);
        if (result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody Announcement announcement) {
        if (announcementService.readById(id) == null) return ResponseEntity.notFound().build();
        announcementService.update(announcement, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        if (announcementService.readById(id) == null) return ResponseEntity.notFound().build();
        announcementService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Announcement> readAll() {
        List<Announcement> announcements = announcementService.readAll();
        return announcements.isEmpty() ? new ArrayList<>() : announcements;
    }

}
