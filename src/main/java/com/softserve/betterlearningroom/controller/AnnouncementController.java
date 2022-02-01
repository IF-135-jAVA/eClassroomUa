package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.AnnouncementDTO;
import com.softserve.betterlearningroom.service.AnnouncementService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@AllArgsConstructor
@RequestMapping("/api/classrooms/{classroomId}/announcements")
@Slf4j
public class AnnouncementController {
    private AnnouncementService announcementService;

    @PostMapping
    @PreAuthorize("hasAuthority('announcement:write')")
    public ResponseEntity<AnnouncementDTO> save(@RequestBody AnnouncementDTO announcementDTO, @PathVariable Long classroomId) {
        announcementDTO.setCourseId(classroomId);
        log.info("New announcement has been created");
        return new ResponseEntity<>(announcementService.save(announcementDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('announcement:read')")
    public ResponseEntity<List<AnnouncementDTO>> findByCourseId(@PathVariable Long classroomId) {
        log.info("Loaded all announcements with courseId " + classroomId);
        return ResponseEntity.ok(announcementService.findByCourseId(classroomId));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('announcement:read')")
    public ResponseEntity<AnnouncementDTO> findById(@PathVariable Long id) {
        log.info("Loaded information about announcement with id " + id);
        return ResponseEntity.ok(announcementService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<AnnouncementDTO> update(@PathVariable Long id, @RequestBody AnnouncementDTO announcementDTO) {
        log.info("Updated announcement with id " + id);
        return ResponseEntity.ok(announcementService.update(announcementDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AnnouncementDTO> delete(@PathVariable Long id) {
        announcementService.delete(id);
        log.info("Deleted announcement with id " + id);
        return ResponseEntity.noContent().build();
    }
}











