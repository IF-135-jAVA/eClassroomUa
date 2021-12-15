package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.UserAssignmentDto;
import com.softserve.betterlearningroom.service.UserAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/classrooms/{classroomId}/materials/{materialId}/user-assignments")
@AllArgsConstructor
public class UserAssignmentController {

    private UserAssignmentService userAssignmentService;

    @PostMapping
    public ResponseEntity<UserAssignmentDto> create(@RequestBody UserAssignmentDto userAssignmentDto) {
        long createdDtoId = userAssignmentService.create(userAssignmentDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdDtoId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserAssignmentDto> readById(@PathVariable long id) {
        UserAssignmentDto result = userAssignmentService.readById(id);
        if(result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserAssignmentDto> update(@PathVariable long id, @RequestBody UserAssignmentDto userAssignmentDto) {
        if(userAssignmentService.readById(id) == null) return ResponseEntity.notFound().build();
        userAssignmentService.update(userAssignmentDto, id);
        return ResponseEntity.ok(userAssignmentService.readById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserAssignmentDto>> getByAssignment(@PathVariable long materialId) {
        return ResponseEntity.ok(userAssignmentService.getByAssignment(materialId));
    }
}
