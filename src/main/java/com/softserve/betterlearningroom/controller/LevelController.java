package com.softserve.betterlearningroom.controller;


import com.softserve.betterlearningroom.dto.LevelDTO;
import com.softserve.betterlearningroom.service.impl.LevelServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/classrooms/{classroomId}/topics/{topicId}/materials/{materialId}/criterions/{criterionId}/level")
public class LevelController {

    @Autowired
    private LevelServiceImpl levelServiceImpl;

    @GetMapping("/all")
    public ResponseEntity<List<LevelDTO>> findAll() {
        List<LevelDTO> level = levelServiceImpl.findAll();
        return ResponseEntity.ok().body(level);
    }

    @GetMapping
    public ResponseEntity<List<LevelDTO>> findAllByCriterionId(@PathVariable(value = "criterionId" ) final Long criterionId) {
        List<LevelDTO> level = levelServiceImpl.findAllByCriterionId(criterionId);
        return ResponseEntity.ok().body(level);
    }

    @GetMapping("/deleted/{deleted}") //TODO remove
    public ResponseEntity<List<LevelDTO>> findAllDeleted() {
        List<LevelDTO> level = levelServiceImpl.findAllDeleted();
        return ResponseEntity.ok().body(level);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LevelDTO> findById(@PathVariable(value = "id") final Long levelId) {
        LevelDTO levelDTO = levelServiceImpl.findById(levelId);
        return ResponseEntity.ok().body(levelDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LevelDTO> save(@Valid @RequestBody LevelDTO levelDTO) {
        return new ResponseEntity<>(levelServiceImpl.save(levelDTO), HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody final LevelDTO levelDTO) {
        return new ResponseEntity<>(levelServiceImpl.update(levelDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id) {

        levelServiceImpl.delete(id);
    }
}