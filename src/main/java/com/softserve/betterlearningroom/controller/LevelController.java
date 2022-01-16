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

    /**
     * get all exist level
     */
    @GetMapping
    public ResponseEntity<List<LevelDTO>> getAll() {
        List<LevelDTO> level = levelServiceImpl.findAll();
        return ResponseEntity.ok().body(level);
    }

    /**
     * get all exist level by criterion id
     */
    @GetMapping("/byCriterionId/{byCriterionId}")
    public ResponseEntity<List<LevelDTO>> getAllByCriterionId(@PathVariable(value = "byCriterionId" ) final Long criterionId) {
        List<LevelDTO> level = levelServiceImpl.findAllByCriterionId(criterionId);
        return ResponseEntity.ok().body(level);
    }

    /**
     * get deleted levels
     */
    @GetMapping("/deleted/{deleted}")
    public ResponseEntity<List<LevelDTO>> getAllDeleted(@PathVariable String deleted) {
        List<LevelDTO> level = levelServiceImpl.findAllDeleted();
        return ResponseEntity.ok().body(level);
    }

    /**
     * get level by id
     */
    // @ApiOperation("Find level by id")
    @GetMapping("/{id}")
    public ResponseEntity<LevelDTO> getById(@PathVariable(value = "id") final Long levelId) {
        LevelDTO levelDTO = levelServiceImpl.findById(levelId);
        return ResponseEntity.ok().body(levelDTO);
    }

    /**
     * create level
     */
    // @ApiOperation("Create level")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LevelDTO> createLevel(@Valid @RequestBody LevelDTO levelDTO) {

        return new ResponseEntity<>(levelServiceImpl.save(levelDTO), HttpStatus.CREATED);
    }

    /**
     * update table by id
     */

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody final LevelDTO levelDTO) {

        return new ResponseEntity<>(levelServiceImpl.update(levelDTO), HttpStatus.ACCEPTED);
    }

    /**
     * delete by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id) {

        levelServiceImpl.removeById(id);
    }

}
