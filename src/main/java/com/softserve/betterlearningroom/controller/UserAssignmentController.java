package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.UserAssignmentDTO;
import com.softserve.betterlearningroom.service.UserAssignmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/materials/{materialId}/assignments")
@AllArgsConstructor
public class UserAssignmentController {

    private UserAssignmentService userAssignmentService;

    @PostMapping
    public ResponseEntity<UserAssignmentDTO> create(@RequestBody UserAssignmentDTO userAssignmentDTO, @PathVariable long materialId) {
        userAssignmentDTO.setMaterialId(materialId);
        return new ResponseEntity<>(userAssignmentService.create(userAssignmentDTO), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserAssignmentDTO> readById(@PathVariable long id) {
        return ResponseEntity.ok(userAssignmentService.readById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserAssignmentDTO> update(@PathVariable long id, @RequestBody UserAssignmentDTO userAssignmentDTO) {
        return ResponseEntity.ok(userAssignmentService.update(userAssignmentDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserAssignmentDTO> delete(@PathVariable long id) {
        userAssignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserAssignmentDTO>> getByAssignment(@PathVariable long materialId) {
        return ResponseEntity.ok(userAssignmentService.getByAssignment(materialId));
    }
}
