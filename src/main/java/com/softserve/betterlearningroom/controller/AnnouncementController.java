package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.AnnouncementDTO;
import com.softserve.betterlearningroom.service.AnnouncementService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@AllArgsConstructor
@RequestMapping("/api/classrooms/{classroomId}/announcements")
public class AnnouncementController {
    private AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<AnnouncementDTO> create(@RequestBody AnnouncementDTO announcementDTO, @PathVariable long classroomId) {
        announcementDTO.setCourseId(classroomId);
        return new ResponseEntity<>(announcementService.create(announcementDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AnnouncementDTO>> readByCourseId(@PathVariable long classroomId) {
        return ResponseEntity.ok(announcementService.readByCourseId(classroomId));
    }

    @GetMapping("{id}")
    public ResponseEntity<AnnouncementDTO> readById(@PathVariable long id) {
        return ResponseEntity.ok(announcementService.readById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<AnnouncementDTO> update(@PathVariable long id, @RequestBody AnnouncementDTO announcementDTO) {
        return ResponseEntity.ok(announcementService.update(announcementDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AnnouncementDTO> delete(@PathVariable long id) {
        announcementService.delete(id);
        return ResponseEntity.noContent().build();
    }

}











