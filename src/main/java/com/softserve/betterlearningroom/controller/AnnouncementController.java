package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.AnnouncementDTO;
import com.softserve.betterlearningroom.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/classrooms/{classroomId}/announcements")
public class AnnouncementController {
    private AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<AnnouncementDTO> create(@RequestBody AnnouncementDTO announcementDTO) {
        announcementService.create(announcementDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AnnouncementDTO>> readAll() {
        List<AnnouncementDTO> announcements = announcementService.readAll();
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<AnnouncementDTO> readById(@PathVariable long id) {
        AnnouncementDTO result = announcementService.readById(id);
        if (result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
    @PutMapping("{id}")
    public ResponseEntity<AnnouncementDTO> update(@PathVariable long id, @RequestBody AnnouncementDTO announcementDTO) {
        if (announcementService.readById(id) == null) return ResponseEntity.notFound().build();
        announcementService.update(announcementDTO, id);
        return ResponseEntity.ok(announcementService.readById(id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<AnnouncementDTO> delete(@PathVariable long id) {
        announcementService.delete(id);
        return ResponseEntity.ok().build();
    }
}











