package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.service.MaterialService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/classrooms/{classroomId}/topics/{topicId}/materials")
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping("{id}")
    public ResponseEntity<MaterialDTO> findMaterialById(@PathVariable Long id) {
        return ResponseEntity.ok().body(materialService.findMaterialById(id));
    }

    @GetMapping("/byClassroom")
    public ResponseEntity<List<MaterialDTO>> findAllMaterials(@PathVariable String classroomId) {
        return ResponseEntity.ok().body(materialService.findAllMaterialsByClassroomId(classroomId));
    }

    @GetMapping
    public ResponseEntity<List<MaterialDTO>> findMaterialsByTopic(@PathVariable String classroomId, @PathVariable Long topicId) {
        return ResponseEntity.ok().body(materialService.findAllMaterialsByClassroomId(classroomId));
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<MaterialDTO> save(@RequestBody MaterialDTO material, @PathVariable Long topicId) {
        return new ResponseEntity<>(materialService.save(material, topicId), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<MaterialDTO> update(@RequestBody MaterialDTO material) {
        return new ResponseEntity<>(materialService.update(material), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        materialService.delete(id);
        return ResponseEntity.ok().build();
    }
}