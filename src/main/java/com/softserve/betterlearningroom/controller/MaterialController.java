package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/classrooms/{classroomId}/topics/{topicId}/materials")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService){
        this.materialService = materialService;
    }

    @GetMapping("{id}")
    private ResponseEntity<MaterialDTO> findMaterialById(@PathVariable Long id){
        return ResponseEntity.ok().body(materialService.getMaterialById(id));
    }

    @GetMapping
    private ResponseEntity<List<MaterialDTO>> findAllMaterials(@PathVariable Long classroomId){
        return ResponseEntity.ok().body(materialService.getMaterialsByClassroom(classroomId));
    }

    @PostMapping
    private ResponseEntity<MaterialDTO> createMaterial(@RequestBody MaterialDTO material, @PathVariable Long topicId){
        materialService.addMaterial(material, topicId);
        return new ResponseEntity<>(materialService.addMaterial(material, topicId),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    private ResponseEntity<?> updateMaterial(@RequestBody MaterialDTO material){
        materialService.updateMaterial(material);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<?> deleteMaterial(@RequestBody MaterialDTO material){
        materialService.updateMaterial(material);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}