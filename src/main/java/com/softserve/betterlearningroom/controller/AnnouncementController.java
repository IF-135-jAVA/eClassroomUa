package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.AnnouncementDTO;
import com.softserve.betterlearningroom.service.AnnouncementService;
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
// TODO: 25.12.2021 we have global CORS config, please remove it
//@CrossOrigin(
//        allowCredentials = "true",
//        origins = "http://localhost:4200",
//        allowedHeaders = "*",
//        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}
//)
@AllArgsConstructor
@RequestMapping("/api/classrooms/{classroomId}/announcements")
public class AnnouncementController {
    private AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AnnouncementDTO announcementDTO) {
        announcementService.create(announcementDTO);
        // TODO: 25.12.2021 return created object to UI as well
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AnnouncementDTO>> readAll(@PathVariable int classroomId) {
        // TODO: 25.12.2021 use classromId to get all the announcement for a specific classroom
        List<AnnouncementDTO> announcements = announcementService.readAll();
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementDTO> readById(@PathVariable int classroomId, @PathVariable int id) {
        // TODO: 25.12.2021 use classromId to get all the announcement for a specific classroom
        AnnouncementDTO announcementDTO = announcementService.readById(id);
        return ResponseEntity.ok().body(announcementDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody AnnouncementDTO announcementDTO) {
        announcementService.update(announcementDTO, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        announcementService.delete(id);
        return ResponseEntity.ok().build();

    }
}






