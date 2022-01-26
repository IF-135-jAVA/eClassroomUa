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
    public ResponseEntity<AnnouncementDTO> save(@RequestBody AnnouncementDTO announcementDTO, @PathVariable Long classroomId) {
        announcementDTO.setCourseId(classroomId);
        return new ResponseEntity<>(announcementService.save(announcementDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AnnouncementDTO>> findByCourseId(@PathVariable Long classroomId) {
        return ResponseEntity.ok(announcementService.findByCourseId(classroomId));
    }

    @GetMapping("{id}")
    public ResponseEntity<AnnouncementDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(announcementService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<AnnouncementDTO> update(@PathVariable Long id, @RequestBody AnnouncementDTO announcementDTO) {
        return ResponseEntity.ok(announcementService.update(announcementDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AnnouncementDTO> delete(@PathVariable Long id) {
        announcementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}











