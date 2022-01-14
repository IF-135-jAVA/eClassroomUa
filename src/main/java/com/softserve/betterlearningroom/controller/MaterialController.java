package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.service.MaterialService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@CrossOrigin(
        allowCredentials = "true",
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
)
@RequestMapping("/api/classrooms/{classroomId}/topics/{topicId}/materials")
public class

MaterialController {

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
        return new ResponseEntity<>(materialService.addMaterial(material, topicId),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    private ResponseEntity<MaterialDTO> updateMaterial(@RequestBody MaterialDTO material){
        return new ResponseEntity<>(materialService.updateMaterial(material),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<?> deleteMaterial(@RequestBody MaterialDTO material){
        materialService.updateMaterial(material);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}