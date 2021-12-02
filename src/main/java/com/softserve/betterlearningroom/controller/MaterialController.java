package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.model.Material;
import com.softserve.betterlearningroom.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/{classroom_id}/materials")
public class MaterialController {

    private MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService){
        this.materialService = materialService;
    }

    @GetMapping("{id}")
    private ResponseEntity<Material> findMaterialById(@PathVariable Long id){
        return ResponseEntity.ok().body(materialService.getMaterialById(id));
    }

    @GetMapping
    private ResponseEntity<List<? extends Material>> findAllMaterials(@PathVariable Long classroom_id){
        return ResponseEntity.ok().body(materialService.getMaterialsByClassroom(classroom_id));
    }

    @PostMapping("new-material")
    private ResponseEntity<?> createMaterial(@RequestBody Material material){
        materialService.addMaterial(material);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}/update-material")
    private ResponseEntity<?> updateMaterial(@RequestBody Material material){
        materialService.updateMaterial(material);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
