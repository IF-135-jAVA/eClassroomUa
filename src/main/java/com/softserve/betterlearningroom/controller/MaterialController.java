package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.service.MaterialService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/api/classrooms/{classroomId}/topics/{topicId}/materials")
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping("{id}")
    public ResponseEntity<MaterialDTO> findMaterialById(@PathVariable Long id) {
        return ResponseEntity.ok().body(materialService.getMaterialById(id));
    }

    @GetMapping("/byClassroom")
    public ResponseEntity<List<MaterialDTO>> findAllMaterials(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(materialService.getMaterialsByClassroom(classroomId));
    }

    @GetMapping
    public ResponseEntity<List<MaterialDTO>> findMaterialsByTopic(@PathVariable Long classroomId, @PathVariable Long topicId) {
        return ResponseEntity.ok().body(materialService.getMaterialsByTopic(classroomId, topicId));
    }

    @PostMapping
    public ResponseEntity<MaterialDTO> createMaterial(@RequestBody MaterialDTO material, @PathVariable Long topicId) {
        return new ResponseEntity<>(materialService.addMaterial(material, topicId), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<MaterialDTO> updateMaterial(@RequestBody MaterialDTO material) {
        return new ResponseEntity<>(materialService.updateMaterial(material), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMaterial(@RequestBody MaterialDTO material) {
        materialService.updateMaterial(material);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}